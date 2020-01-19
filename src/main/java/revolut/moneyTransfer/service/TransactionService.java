package revolut.moneyTransfer.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import jooq.generated.code.tables.records.AccountRecord;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import revolut.moneyTransfer.enums.TransactionType;
import revolut.moneyTransfer.enums.TransferStatus;
import revolut.moneyTransfer.exception.BalanceNotSufficientException;
import revolut.moneyTransfer.repository.AccountRepository;
import revolut.moneyTransfer.repository.TransferRepository;
import revolut.moneyTransfer.domain.Transaction;
import revolut.moneyTransfer.domain.Transfer;
import revolut.moneyTransfer.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@Singleton
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;
    // DSLContext is required here to perform multiple operations in one transaction
    private final DSLContext dslContext;

    @Inject
    public TransactionService(TransactionRepository transactionRepository,
                              TransferRepository transferRepository,
                              AccountRepository accountRepository,
                              DSLContext dslContext) {
        this.transactionRepository = transactionRepository;
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
        this.dslContext = dslContext;
    }

    public void debitCustomerAccount(final UUID accountId, final Transfer transfer) {
        log.info("Creating debit transaction for transfer id {}", transfer.getId());
        dslContext.transaction(config -> {
            DSLContext transactionalContext = DSL.using(config);
            AccountRecord accountRecord = accountRepository.acquireLockOnAccount(transactionalContext, accountId);
            validateBalanceForTransaction(accountRecord, transfer.getAmount());
            Transaction transaction = buildTransaction(accountId, transfer.getId(), TransactionType.DEBIT, transfer.getAmount().negate());
            transactionRepository.save(transactionalContext, transaction);
            BigDecimal newBalance = accountRecord.getCurrentBalance().subtract(transfer.getAmount());
            accountRepository.updateCurrentBalance(transactionalContext, accountId, newBalance);
            transferRepository.modifyTransferStatus(transactionalContext, transfer.getId(), TransferStatus.SENDER_ACCOUNT_CHARGED);

        });
    }


    public void creditCustomerAccount(final UUID accountId, final Transfer transfer) {
        log.info("Creating credit transaction for transfer id {}", transfer.getId());
        dslContext.transaction(config -> {
            DSLContext transactionalContext = DSL.using(config);
            AccountRecord accountRecord = accountRepository.acquireLockOnAccount(transactionalContext, accountId);
            Transaction transaction = buildTransaction(accountId, transfer.getId(), TransactionType.CREDIT, transfer.getAmount());
            transactionRepository.save(transactionalContext, transaction);
            BigDecimal newBalance = accountRecord.getCurrentBalance().add(transfer.getAmount());
            accountRepository.updateCurrentBalance(transactionalContext, accountId, newBalance);
            transferRepository.modifyTransferStatus(transactionalContext, transfer.getId(), TransferStatus.COMPLETED);
        });
    }


    private void validateBalanceForTransaction(final AccountRecord accountRecord, final BigDecimal transactionAmount) {
        if (accountRecord.getCurrentBalance().compareTo(transactionAmount) < 0) {
            throw new BalanceNotSufficientException(format("Balance not sufficient in account %s to make this transaction", accountRecord.getId()));
        }
    }

    private Transaction buildTransaction(final UUID accountId, final UUID transferId, final TransactionType transactionType, final BigDecimal amount) {
        return Transaction.builder()
                .id(UUID.randomUUID())
                .accountId(accountId)
                .amount(amount)
                .transactionType(transactionType.getCode())
                .transferId(transferId)
                .build();
    }
}

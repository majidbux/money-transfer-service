package revolut.moneyTransfer.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.jooq.exception.DataAccessException;
import revolut.moneyTransfer.domain.Account;
import revolut.moneyTransfer.domain.Transfer;
import revolut.moneyTransfer.dto.TransferRequestDTO;
import revolut.moneyTransfer.enums.TransferStatus;
import revolut.moneyTransfer.exception.BalanceNotSufficientException;
import revolut.moneyTransfer.exception.MoneyTransferBaseException;
import revolut.moneyTransfer.repository.AccountRepository;
import revolut.moneyTransfer.repository.TransferRepository;
import revolut.moneyTransfer.utils.ObjectUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@Singleton
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private final TransactionService transactionService;

    @Inject
    public TransferService(AccountRepository accountRepository,
                           TransferRepository transferRepository,
                           TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
        this.transactionService = transactionService;
    }

    public Transfer createTransfer(final TransferRequestDTO requestDTO) {
        log.info("Received request to transfer from account id {}, to account id {}", requestDTO.getFromAccountId(),
                requestDTO.getToAccountId());
        //validate if to account exists
        accountRepository.findById(requestDTO.getToAccountId());
        Account fromAccount = accountRepository.findById(requestDTO.getFromAccountId());
        validateBalanceForTransaction(fromAccount, requestDTO.getAmount());
        Transfer transfer = createNewTransfer(UUID.randomUUID(), requestDTO);
        makeTransactions(transfer);
        return transferRepository.findById(transfer.getId());
    }

    private Transfer createNewTransfer(final UUID transferId, final TransferRequestDTO requestDTO) {
        Transfer transfer = new Transfer();
        transfer.setId(transferId);
        transfer.setStatus(TransferStatus.PENDING.getCode());
        ObjectUtils.copyProperties(transfer, requestDTO);
        transferRepository.save(transfer);
        log.info("Created transfer {}, status {}", transfer.getId(), transfer.getStatus());
        return transfer;
    }

    private void makeTransactions(final Transfer transfer) {
        try {
            transactionService.debitCustomerAccount(transfer.getFromAccountId(), transfer);
            transactionService.creditCustomerAccount(transfer.getToAccountId(), transfer);
        } catch (DataAccessException e) {

        } catch (MoneyTransferBaseException e) {
            transferRepository.markTransferFailed(transfer.getId(), e.getMessage());
        }
    }

    private void validateBalanceForTransaction(final Account account, final BigDecimal transactionAmount) {
        if (account.getCurrentBalance().compareTo(transactionAmount) < 0) {
            throw new BalanceNotSufficientException(format("Balance not sufficient in account %s to make this transaction", account.getId()));
        }
    }
}

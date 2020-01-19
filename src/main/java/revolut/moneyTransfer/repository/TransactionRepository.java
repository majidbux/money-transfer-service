package revolut.moneyTransfer.repository;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import jooq.generated.code.Tables;
import lombok.extern.slf4j.Slf4j;
import revolut.moneyTransfer.domain.Transaction;
import org.jooq.DSLContext;
import revolut.moneyTransfer.exception.NotFoundException;

import java.util.UUID;

import static java.lang.String.format;

@Singleton
@Slf4j
public class TransactionRepository {

    private final DSLContext dslContext;

    @Inject
    public TransactionRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    /**
     * @param transactionalContext here we should use external provided transactional context instead of repository
     * @param transaction transaction object to save
     */
    public void save(final DSLContext transactionalContext, final Transaction transaction) {
       transactionalContext.newRecord(Tables.TRANSACTION, transaction).store();
    }

    public Transaction findById(final UUID transactionId) {
        return dslContext.selectFrom(Tables.TRANSACTION)
                .where(Tables.TRANSACTION.ID.eq(transactionId))
                .fetchOptional()
                .orElseThrow(() -> new NotFoundException(format("Transaction %s not found", transactionId)))
                .into(Transaction.class);
    }
}
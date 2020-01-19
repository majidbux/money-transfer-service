package revolut.moneyTransfer.repository;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import jooq.generated.code.Tables;
import jooq.generated.code.tables.records.AccountRecord;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import revolut.moneyTransfer.domain.Account;
import revolut.moneyTransfer.exception.NotFoundException;

import java.math.BigDecimal;
import java.util.UUID;

import static java.lang.String.format;

@Singleton
@Slf4j
public class AccountRepository {

    private final DSLContext dslContext;

    @Inject
    public AccountRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Account findById(final UUID accountId) {
        return dslContext.selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.ID.eq(accountId))
                .fetchOptional()
                .orElseThrow(() -> new NotFoundException(format("Account %s not found", accountId)))
                .into(Account.class);
    }

    /**
     * Need to lock account record else other threads can update record in parallel
     *
     * @param transactionalContext here we should use external provided transactional context instead of repository
     * @param accountId            account id to lock account
     * @return AccountRecord
     */
    public AccountRecord acquireLockOnAccount(final DSLContext transactionalContext, final UUID accountId) {
        return transactionalContext.selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.ID.eq(accountId))
                .forUpdate()
                .fetchOne();
    }

    /**
     * Update current balance of an account after transaction
     *
     * @param transactionalContext here we should use external provided transactional context instead of repository
     * @param accountId            account id to update balance
     * @param newBalance           new balance
     */
    public void updateCurrentBalance(final DSLContext transactionalContext, final UUID accountId, final BigDecimal newBalance) {
        transactionalContext.update(Tables.ACCOUNT)
                .set(Tables.ACCOUNT.CURRENT_BALANCE, newBalance)
                .where(Tables.ACCOUNT.ID.eq(accountId))
                .execute();
    }
}
package revolut.moneyTransfer.repository;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import jooq.generated.code.Tables;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import revolut.moneyTransfer.domain.Transfer;
import revolut.moneyTransfer.enums.TransferStatus;
import revolut.moneyTransfer.exception.NotFoundException;

import java.util.UUID;

import static java.lang.String.format;

@Singleton
@Slf4j
public class TransferRepository {

    private final DSLContext dslContext;

    @Inject
    public TransferRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void save(final Transfer transfer) {
        dslContext.newRecord(Tables.TRANSFER, transfer).store();
    }

    public void modifyTransferStatus(final DSLContext transactionalContext, final UUID transferId, final TransferStatus status) {
        transactionalContext.update(Tables.TRANSFER)
                .set(Tables.TRANSFER.STATUS, status.getCode())
                .where(Tables.TRANSFER.ID.eq(transferId))
                .execute();
    }

    public void markTransferFailed(final UUID transferId, final String failureReason) {
        dslContext.update(Tables.TRANSFER)
                .set(Tables.TRANSFER.STATUS, TransferStatus.FAILED.getCode())
                .set(Tables.TRANSFER.FAILURE_REASON, failureReason)
                .where(Tables.TRANSFER.ID.eq(transferId))
                .execute();
    }

    public Transfer findById(final UUID transferId) {
        return dslContext.selectFrom(Tables.TRANSFER)
                .where(Tables.TRANSFER.ID.eq(transferId))
                .fetchOptional()
                .orElseThrow(() -> new NotFoundException(format("Transfer %s not found", transferId)))
                .into(Transfer.class);
    }
}
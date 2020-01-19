package revolut.moneyTransfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Transaction {

    @Column(name = "ID")
    private UUID id;

    @Column(name = "TYPE")
    private Integer transactionType;

    @Column(name = "ACCOUNT_ID")
    private UUID accountId;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "TRANSFER_ID")
    private UUID transferId;
}

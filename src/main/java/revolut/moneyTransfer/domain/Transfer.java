package revolut.moneyTransfer.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Transfer {

    @Column(name = "ID")
    private UUID id;

    @Column(name = "FROM_ACCOUNT_ID")
    private UUID fromAccountId;

    @Column(name = "TO_ACCOUNT_ID")
    private UUID toAccountId;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DETAILS")
    private String details;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "FAILURE_REASON")
    private String failureReason;
}

package revolut.moneyTransfer.domain;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Account {

    @Column(name = "ID")
    private UUID id;

    @Column(name = "BANK_ID")
    private UUID bankId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CUSTOMER_ID")
    private UUID customerId;

    @Column(name = "CURRENT_BALANCE")
    private BigDecimal currentBalance;

    @Column(name = "CURRENCY_ID")
    private Integer currencyId;

    @Column(name = "TYPE_ID")
    private Integer type;

    @Column(name = "STATUS")
    private Integer status;
}

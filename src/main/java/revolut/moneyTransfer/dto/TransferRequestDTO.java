package revolut.moneyTransfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferRequestDTO {

    @NotNull(message = "from account can not be null")
    private UUID fromAccountId;
    @NotNull(message = "to account can not be null")
    private UUID toAccountId;
    @DecimalMin(value = "1.0", message = "Amount should be greater than 0.0")
    private BigDecimal amount;
    @NotBlank(message = "details can not be blank")
    private String details;
}

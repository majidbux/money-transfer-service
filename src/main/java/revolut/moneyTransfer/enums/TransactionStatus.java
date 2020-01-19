package revolut.moneyTransfer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Optional;

@AllArgsConstructor
public enum TransactionStatus {
    NEW(0),
    FAILED(1),
    CANCELLED(2),
    SENT(3);

    @Getter
    private Integer code;

    public static Optional<TransactionStatus> fromCode(Integer code) {
        return EnumSet.allOf(TransactionStatus.class).stream()
                .filter(t -> t.getCode().equals(code))
                .findFirst();
    }

    @Override
    public String toString() {
        return code.toString();
    }
}

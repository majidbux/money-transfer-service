package revolut.moneyTransfer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Optional;

@AllArgsConstructor
public enum TransactionType {
    DEBIT(0),
    CREDIT(1);

    @Getter
    private Integer code;

    public static Optional<TransactionType> fromCode(Integer code) {
        return EnumSet.allOf(TransactionType.class).stream()
                .filter(t -> t.getCode().equals(code))
                .findFirst();
    }

    @Override
    public String toString() {
        return code.toString();
    }
}

package revolut.moneyTransfer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Optional;

@AllArgsConstructor
public enum TransferStatus {
    PENDING(0),
    SENDER_ACCOUNT_CHARGED(1),
    FAILED(2),
    COMPLETED(3);

    @Getter
    private Integer code;

    public static Optional<TransferStatus> fromCode(Integer code) {
        return EnumSet.allOf(TransferStatus.class).stream()
                .filter(t -> t.getCode().equals(code))
                .findFirst();
    }

    @Override
    public String toString() {
        return code.toString();
    }
}

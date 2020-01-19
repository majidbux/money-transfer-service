package revolut.moneyTransfer.exception;

public abstract class MoneyTransferBaseException extends RuntimeException {

    public abstract Integer getStatus();

    public MoneyTransferBaseException(final String message) {
        super(message);
    }
}

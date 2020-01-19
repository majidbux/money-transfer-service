package revolut.moneyTransfer.exception;


import org.eclipse.jetty.http.HttpStatus;

public class BalanceNotSufficientException extends MoneyTransferBaseException {

    @Override
    public Integer getStatus() {
        return HttpStatus.BAD_REQUEST_400;
    }

    public BalanceNotSufficientException(final String message) {
        super(message);
    }
}

package revolut.moneyTransfer.exception;


import org.eclipse.jetty.http.HttpStatus;

public class ValidationException extends MoneyTransferBaseException {

    @Override
    public Integer getStatus() {
        return HttpStatus.BAD_REQUEST_400;
    }

    public ValidationException(final String message) {
        super(message);
    }
}

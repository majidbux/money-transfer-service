package revolut.moneyTransfer.exception;


import org.eclipse.jetty.http.HttpStatus;

public class NotFoundException extends MoneyTransferBaseException {

    @Override
    public Integer getStatus() {
        return HttpStatus.NOT_FOUND_404;
    }

    public NotFoundException (final String message) {
        super(message);
    }
}

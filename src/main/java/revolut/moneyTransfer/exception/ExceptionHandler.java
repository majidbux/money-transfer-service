package revolut.moneyTransfer.exception;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.jetty.http.HttpStatus;
import org.jooq.exception.DataAccessException;
import revolut.moneyTransfer.dto.ErrorResponseDTO;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;

@Singleton
public class ExceptionHandler {

    private final Gson gson;

    @Inject
    public ExceptionHandler(Gson gson) {
        this.gson = gson;
    }

    public void handleMoneyTransferExceptions(final MoneyTransferBaseException e, final Request request, final Response response) {
        response.status(e.getStatus());
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(LocalDateTime.now().toString(), e.getMessage(), e.getStatus());
        response.body(gson.toJson(errorResponseDTO));
    }

    public void handleInternalServerError(final DataAccessException e, final Request request, final Response response) {
        response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(LocalDateTime.now().toString(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR_500);
        response.body(gson.toJson(errorResponseDTO));
    }
}

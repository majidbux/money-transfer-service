package revolut.moneyTransfer;


import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.http.HttpStatus;
import revolut.moneyTransfer.dto.TransferRequestDTO;
import revolut.moneyTransfer.validation.ValidationHandler;
import revolut.moneyTransfer.domain.Transfer;
import revolut.moneyTransfer.service.TransferService;
import spark.Request;
import spark.Response;

import static spark.Spark.post;

@Singleton
@Slf4j
public class TransferController {

    private final Gson gson;
    private final TransferService transferService;
    private final ValidationHandler<TransferRequestDTO> validationHandler;

    @Inject
    public TransferController(Gson gson, TransferService transferService, ValidationHandler<TransferRequestDTO> validationHandler) {
        this.gson = gson;
        this.transferService = transferService;
        this.validationHandler = validationHandler;
    }

    public void init() {
        post("/api/transfer", this::createTransfer, gson::toJson);
    }

    public Transfer createTransfer(Request request, Response response) {
        TransferRequestDTO requestDTO = gson.fromJson(request.body(), TransferRequestDTO.class);
        validationHandler.validateRequest(requestDTO);
        Transfer transfer = transferService.createTransfer(requestDTO);
        response.status(HttpStatus.CREATED_201);
        return transfer;
    }
}

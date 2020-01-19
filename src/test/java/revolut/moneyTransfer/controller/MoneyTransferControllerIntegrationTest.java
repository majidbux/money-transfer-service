package revolut.moneyTransfer.controller;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;
import revolut.moneyTransfer.BaseIntegrationTest;
import revolut.moneyTransfer.TestDataProvider;
import revolut.moneyTransfer.domain.Account;
import revolut.moneyTransfer.domain.Transfer;
import revolut.moneyTransfer.dto.TransferRequestDTO;
import revolut.moneyTransfer.enums.TransferStatus;
import revolut.moneyTransfer.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class MoneyTransferControllerIntegrationTest extends BaseIntegrationTest {


    @Test
    void testShouldReturnBadRequestWhenTransferAmountIsInvalid() {
        TransferRequestDTO requestDTO = buildTransferRequestDTO();
        requestDTO.setAmount(BigDecimal.valueOf(0));
        validateErrorIsReceived(requestDTO, HttpStatus.BAD_REQUEST_400, "Amount should be greater than 0.0");
    }

    @Test
    void testShouldReturnBadRequestWhenFromAccountIsInvalid() {
        TransferRequestDTO requestDTO = buildTransferRequestDTO();
        requestDTO.setFromAccountId(null);
        validateErrorIsReceived(requestDTO, HttpStatus.BAD_REQUEST_400, "from account can not be null");
    }

    @Test
    void testShouldReturnBadRequestWhenToAccountIsInvalid() {
        TransferRequestDTO requestDTO = buildTransferRequestDTO();
        requestDTO.setToAccountId(null);
        validateErrorIsReceived(requestDTO, HttpStatus.BAD_REQUEST_400, "to account can not be null");
    }

    @Test
    void testShouldReturnBadRequestWhenTransferDetailIsNull() {
        TransferRequestDTO requestDTO = buildTransferRequestDTO();
        requestDTO.setDetails(null);
        validateErrorIsReceived(requestDTO, HttpStatus.BAD_REQUEST_400, "details can not be blank");
    }

    @Test
    void testShouldReturnBadRequestWhenTransferDetailEmpty() {
        TransferRequestDTO requestDTO = buildTransferRequestDTO();
        requestDTO.setDetails(StringUtils.EMPTY);
        validateErrorIsReceived(requestDTO, HttpStatus.BAD_REQUEST_400, "details can not be blank");
    }

    @Test
    void testShouldReturnBadRequestWhenSenderAccountNotFound() {
        TransferRequestDTO requestDTO = buildTransferRequestDTO();
        requestDTO.setFromAccountId(UUID.randomUUID());
        String errorMessage = format("Account %s not found", requestDTO.getFromAccountId());
        validateErrorIsReceived(requestDTO, HttpStatus.NOT_FOUND_404, errorMessage);
    }

    @Test
    void testShouldReturnBadRequestWhenReceiverAccountNotFound() {
        TransferRequestDTO requestDTO = buildTransferRequestDTO();
        requestDTO.setToAccountId(UUID.randomUUID());
        String errorMessage = format("Account %s not found", requestDTO.getToAccountId());
        validateErrorIsReceived(requestDTO, HttpStatus.NOT_FOUND_404, errorMessage);
    }

    @Test
    void testShouldReturnBadRequestWhenSenderDoesNotHaveSufficientBalance() {
        TransferRequestDTO requestDTO = buildTransferRequestDTO();
        requestDTO.setAmount(BigDecimal.valueOf(1000000));
        String errorMessage = format("Balance not sufficient in account %s to make this transaction", requestDTO.getFromAccountId());
        validateErrorIsReceived(requestDTO, HttpStatus.BAD_REQUEST_400, errorMessage);
    }

    @Test
    void testShouldTransferWhenValidDataIsProvided() {
        TransferRequestDTO requestDTO = buildTransferRequestDTO();
        AccountRepository accountRepository = application.getInstance(AccountRepository.class);
        Account fromAccount = accountRepository.findById(requestDTO.getFromAccountId());
        Account toAccount = accountRepository.findById(requestDTO.getToAccountId());
        BigDecimal fromAccountBalanceBeforeTransfer = fromAccount.getCurrentBalance();
        BigDecimal toAccountBalanceBeforeTransfer = toAccount.getCurrentBalance();
        //@formatter:off
        given()
            .body(requestDTO)
        .when()
            .post("/transfer").prettyPeek()
        .then()
            .statusCode(HttpStatus.CREATED_201)
            .body("id", notNullValue())
            .body("fromAccountId", is(requestDTO.getFromAccountId().toString()))
            .body("toAccountId", is(requestDTO.getToAccountId().toString()))
            .body("amount", is((requestDTO.getAmount().floatValue())))
            .body("details", is(requestDTO.getDetails()))
            .body("status", is(TransferStatus.COMPLETED.getCode()));
        //@formatter:on
        // validate balance after transfer
        fromAccount = accountRepository.findById(requestDTO.getFromAccountId());
        toAccount = accountRepository.findById(requestDTO.getToAccountId());
        assertThat(fromAccount.getCurrentBalance(), is(fromAccountBalanceBeforeTransfer.subtract(requestDTO.getAmount())));
        assertThat(toAccount.getCurrentBalance(), is(toAccountBalanceBeforeTransfer.add(requestDTO.getAmount())));
    }

    @Test
    public void verifyBalanceAfterConcurrencyTransfer() throws InterruptedException {
        TransferRequestDTO transferRequestDTO = buildTransferRequestDTO();
        // Create 100 threads and transfer 100 from account 1 to account 2
        final ExecutorService executorService = Executors.newFixedThreadPool(100);
        List<Callable<Transfer>> transferRequests = new ArrayList<>();
        IntStream.range(0, 100).forEach(i -> {
            transferRequests.add(() -> sendTransferRequest(transferRequestDTO));
        });
        executorService.invokeAll(transferRequests);
        AccountRepository accountRepository = application.getInstance(AccountRepository.class);
        Account fromAccount = accountRepository.findById(transferRequestDTO.getFromAccountId());
        Account toAccount = accountRepository.findById(transferRequestDTO.getToAccountId());
        int amountFromAccount = TestDataProvider.BALANCE_IN_SENDER_ACCOUNT - (transferRequestDTO.getAmount().intValue() * 100);
        int amountToAccount = TestDataProvider.BALANCE_IN_RECEIVER_ACCOUNT + (transferRequestDTO.getAmount().intValue() * 100);
        assertThat(fromAccount.getCurrentBalance().intValue(), is(amountFromAccount));
        assertThat(toAccount.getCurrentBalance().intValue(), is(amountToAccount));
    }

    private Transfer sendTransferRequest(TransferRequestDTO transferRequestDTO) {
        //@formatter:off
       return given()
            .body(transferRequestDTO)
        .when()
            .post("/transfer").prettyPeek()
        .then()
            .statusCode(HttpStatus.CREATED_201)
            .extract().as(Transfer.class);
        //@formatter:on
    }

    private TransferRequestDTO buildTransferRequestDTO() {
        return TransferRequestDTO.builder()
                .amount(BigDecimal.valueOf(100.0))
                .fromAccountId(TestDataProvider.FROM_ACCOUNT_ID)
                .toAccountId(TestDataProvider.TO_ACCOUNT_ID)
                .details("Lunch payment")
                .build();
    }

    private void validateErrorIsReceived(final TransferRequestDTO requestDTO, final int httpStatus, final String reason) {
        //@formatter:off
        given()
            .body(requestDTO)
        .when()
            .post("/transfer").prettyPeek()
        .then()
            .statusCode(httpStatus)
            .body("message", containsStringIgnoringCase(reason));
        //@formatter:on
    }
}
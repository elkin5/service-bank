package co.neoris.service_bank.model.exception;

public class AmountExceededException extends RuntimeException {
    public AmountExceededException(String message) {
        super(message);
    }
}

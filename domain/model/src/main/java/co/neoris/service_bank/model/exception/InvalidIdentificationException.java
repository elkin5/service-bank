package co.neoris.service_bank.model.exception;

public class InvalidIdentificationException extends RuntimeException {
    public InvalidIdentificationException(String message) {
        super(message);
    }
}

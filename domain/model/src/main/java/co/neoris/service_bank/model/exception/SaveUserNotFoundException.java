package co.neoris.service_bank.model.exception;

public class SaveUserNotFoundException extends RuntimeException {
    public SaveUserNotFoundException(String message) {
        super(message);
    }
}

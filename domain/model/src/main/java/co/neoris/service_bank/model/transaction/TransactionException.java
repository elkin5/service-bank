package co.neoris.service_bank.model.transaction;

public class TransactionException extends RuntimeException {
    public TransactionException(String message) {
        super(message);
    }
}

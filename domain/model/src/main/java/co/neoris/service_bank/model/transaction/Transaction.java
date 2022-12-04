package co.neoris.service_bank.model.transaction;

import co.neoris.service_bank.model.account.Account;
import co.neoris.service_bank.model.exception.ValidationDataException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Transaction {
    private BigDecimal value;
    private BigDecimal initialValue;
    private BigDecimal endingValue;
    private TransactionType type;
    private LocalDateTime createdAt;
    private Account account;

    public Transaction(BigDecimal value, BigDecimal initialValue, BigDecimal endingValue, String type, Account account) {
        this.validateData(type);

        this.value = value;
        this.initialValue = initialValue;
        this.endingValue = endingValue;
        this.type = type.equalsIgnoreCase("Retiro") ? TransactionType.RETIRO : TransactionType.DEPOSITO;
        this.createdAt = LocalDateTime.now();
        this.account = account;
    }

    public void validateData(String type) {
        if (value.compareTo(initialValue) > 0) {
            throw new ValidationDataException("Monto invalido: el monto a retirar debe ser mayor al disponible");
        }

        if (!type.equalsIgnoreCase("Retiro") && !type.equalsIgnoreCase("Deposito")) {
            throw new ValidationDataException("Tipo de movimiento errado");
        }
    }

    public enum TransactionType {
        RETIRO("Retiro"), DEPOSITO("Deposito");

        private final String value;

        TransactionType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}

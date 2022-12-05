package co.neoris.service_bank.model.transaction;

import co.neoris.service_bank.model.account.Account;
import co.neoris.service_bank.model.exception.ValidationDataException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    private Long id;
    private BigDecimal value;
    private BigDecimal initialValue;
    private BigDecimal endingValue;
    private TransactionType type;
    private LocalDateTime createdAt;
    private Account account;

    public Transaction(String value, String type, String accountNumber) {
        this.validateObligatoryData(value, type, accountNumber);

        this.id = null;
        this.value = convertToBigDecimal(value);
        this.initialValue = null;
        this.endingValue = null;
        this.type = type.equalsIgnoreCase("Retiro") ? TransactionType.RETIRO : TransactionType.DEPOSITO;
        this.createdAt = LocalDateTime.now();
        this.account = new Account();
        this.account.setNumber(convertToLong(accountNumber));
    }

    public void validateObligatoryData(String value, String type, String accountNumber) {
        if (value == null || value.isEmpty()) {
            throw new ValidationDataException("Valor de trasferencia es obligatorio");
        }

        if (type == null || type.isEmpty()) {
            throw new ValidationDataException("Tipo de cuenta obligatorio");
        }

        if (!type.equalsIgnoreCase("Retiro") && !type.equalsIgnoreCase("Deposito")) {
            throw new ValidationDataException("Tipo de movimiento errado");
        }

        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new ValidationDataException("Valor inicial de cuenta obligatorio");
        }
    }

    private BigDecimal convertToBigDecimal(String value) {
        Long valueLong = convertToLong(value);

        if (valueLong.compareTo(0L) < 0) {
            throw new ValidationDataException("Valor no puede ser menor a cero");
        }

        return BigDecimal.valueOf(valueLong);
    }

    private Long convertToLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException nfe) {
            throw new ValidationDataException("Valor en formato invalido");
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

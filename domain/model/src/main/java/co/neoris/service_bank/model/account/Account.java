package co.neoris.service_bank.model.account;

import co.neoris.service_bank.model.exception.ValidationDataException;
import co.neoris.service_bank.model.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Account {
    private Long number;
    private AccountType type;
    private BigDecimal balance;
    private Boolean state;
    private User user;

    public Account(Long number, String type, BigDecimal balance, Boolean state, User user) {
        this.validateData(type);

        this.number = number;
        this.type = type.equalsIgnoreCase("ahorro") ? AccountType.AHORRO : AccountType.CORRIENTE;
        this.balance = balance;
        this.state = state;
        this.user = user;
    }

    private void validateData(String type) {
        if (this.number == null) {
            throw new ValidationDataException("Numero de cuenta obligatorio");
        }

        if (type == null || type.isEmpty()) {
            throw new ValidationDataException("Tipo de cuenta obligatorio");
        }

        if (!type.equalsIgnoreCase("Ahorro") && !type.equalsIgnoreCase("Corriente")) {
            throw new ValidationDataException("Tipo de cuenta errado");
        }

        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationDataException("Valor inicial no puede ser menor a cero");
        }
    }

    public enum AccountType {
        AHORRO("Ahorro"), CORRIENTE("Corriente");

        private final String value;

        AccountType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}

package co.neoris.service_bank.model.account;

import co.neoris.service_bank.model.exception.ValidationDataException;
import co.neoris.service_bank.model.person.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Account {
    private Long id;
    private Long number;
    private AccountType type;
    private BigDecimal balance;
    private BigDecimal initialValue;
    private Boolean state;
    private Person person;

    public Account(String number, String type, String initialValue, String identification) {
        this.validateObligatoryData(number, type, initialValue, identification);

        this.id = null;
        this.number = convertToLong(number);
        this.type = type.equalsIgnoreCase("ahorro") ? AccountType.AHORRO : AccountType.CORRIENTE;
        this.balance = convertToBigDecimal(initialValue);
        this.initialValue = convertToBigDecimal(initialValue);
        this.state = true;
        this.person = new Person();
        this.person.setIdentification(convertToLong(identification));
    }

    private void validateObligatoryData(String number, String type, String balance, String identification) {
        if (number == null || number.isEmpty()) {
            throw new ValidationDataException("Numero de cuenta obligatorio");
        }

        if (type == null || type.isEmpty()) {
            throw new ValidationDataException("Tipo de cuenta obligatorio");
        }

        if (balance == null || balance.isEmpty()) {
            throw new ValidationDataException("Valor inicial de cuenta obligatorio");
        }

        if (!type.equalsIgnoreCase("Ahorro") && !type.equalsIgnoreCase("Corriente")) {
            throw new ValidationDataException("Tipo de cuenta errado");
        }

        if (identification == null || identification.isEmpty()) {
            throw new ValidationDataException("Se requiere identificación de usuario");
        }
    }

    private BigDecimal convertToBigDecimal(String value) {
        Long valueLong = convertToLong(value);

        if (valueLong.compareTo(0L) < 0) {
            throw new ValidationDataException("Valor inicial no puede ser menor a cero");
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

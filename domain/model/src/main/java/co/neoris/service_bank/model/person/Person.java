package co.neoris.service_bank.model.person;

import co.neoris.service_bank.model.exception.ValidationDataException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@SuperBuilder
public class Person {
    private String completeName;
    private Gender gender;
    private LocalDate birthDate;
    private Long identification;
    private String address;
    private Long phoneNumber;

    public Person(String completeName, String gender, String birthDate, String identification,
                  String address, String phoneNumber) {
        this.validateData(gender);

        this.completeName = completeName;
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.birthDate = this.convertToLocalDate(birthDate);
        this.identification = this.convertToLong(identification);
        this.address = address;
        this.phoneNumber = this.convertToLong(phoneNumber);
    }

    public void validateData(String gender) {
        if (this.completeName == null || this.completeName.isEmpty()) {
            throw new ValidationDataException("Nombre obligatorio");
        }

        if (this.phoneNumber == null) {
            throw new ValidationDataException("Numero de teléfono obligatorio");
        }

        if (gender == null || gender.isEmpty()) {
            throw new ValidationDataException("Nombre obligatorio");
        }

        if (this.identification == null) {
            throw new ValidationDataException("Numero de teléfono obligatorio");
        }

        if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")
                && !gender.equalsIgnoreCase("O")) {
            throw new ValidationDataException("Tipo de cuenta errado");
        }
    }

    private Long convertToLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException nfe) {
            throw new ValidationDataException("Valor en formato invalido");
        }
    }

    private LocalDate convertToLocalDate(String value) {
        try {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        } catch (DateTimeParseException ex) {
            throw new ValidationDataException("Fecha en formato invalido");
        }
    }

    public enum Gender {
        M("Masculino"), F("Femenino"), O("Otro");

        private final String value;

        Gender(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}

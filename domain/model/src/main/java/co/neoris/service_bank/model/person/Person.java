package co.neoris.service_bank.model.person;

import co.neoris.service_bank.model.exception.ValidationDataException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@NoArgsConstructor
public class Person {
    private Long personId;
    private Long identification;
    private String completeName;
    private Gender gender;
    private LocalDate birthDate;
    private String address;
    private Long phoneNumber;
    private Integer years;

    public Person(String completeName, String gender, String birthDate, String identification,
                  String address, String phoneNumber) {
        this.validateData(completeName, phoneNumber, gender, identification);

        this.completeName = completeName;
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.birthDate = this.convertToLocalDate(birthDate);
        this.identification = this.convertToLong(identification);
        this.address = address;
        this.phoneNumber = this.convertToLong(phoneNumber);
    }

    private void validateData(String completeName, String phoneNumber, String gender, String identification) {
        if (completeName == null || completeName.isEmpty()) {
            throw new ValidationDataException("Nombre obligatorio");
        }

        if (gender == null || gender.isEmpty()) {
            throw new ValidationDataException("Genero obligatorio");
        }

        if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")
                && !gender.equalsIgnoreCase("O")) {
            throw new ValidationDataException("Genero no disponible");
        }

        if (phoneNumber == null) {
            throw new ValidationDataException("Numero de teléfono obligatorio");
        }

        if (identification == null) {
            throw new ValidationDataException("Identificación obligatoria");
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
            return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException ex) {
            throw new ValidationDataException("Fecha en formato invalido");
        }
    }

    public Integer getYears() {
        Period age = Period.between(this.birthDate, LocalDate.now());
        return age.getYears();
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

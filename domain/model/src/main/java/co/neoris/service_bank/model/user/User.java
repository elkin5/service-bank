package co.neoris.service_bank.model.user;

import co.neoris.service_bank.model.exception.ValidationDataException;
import co.neoris.service_bank.model.person.Person;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends Person {
    private Boolean state;
    private String password;

    public User(String completeName, String gender, String birthDate, String identification,
                String address, String phoneNumber, String password) {
        super(completeName, gender, birthDate, identification, address, phoneNumber);
        this.validateData();

        this.state = true;
        this.password = password;
    }

    public void validateData() {
        if (this.password == null || this.password.isEmpty()) {
            throw new ValidationDataException("Contraseña obligatorio");
        }
    }
}
package co.neoris.service_bank.model.user;

import co.neoris.service_bank.model.exception.ValidationDataException;
import co.neoris.service_bank.model.person.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User extends Person {
    private Long userId;
    private Boolean state;
    private String password;

    public User(String completeName, String gender, String birthDate, String identification,
                String address, String phoneNumber, String password) {
        super(completeName, gender, birthDate, identification, address, phoneNumber);
        this.validateUserData(password);

        this.userId = null;
        this.state = true;
        this.password = password;
    }

    public void setPerson(Person person) {
        super.setPersonId(person.getPersonId());
        super.setIdentification(person.getIdentification());
        super.setCompleteName(person.getCompleteName());
        super.setAddress(person.getAddress());
        super.setGender(person.getGender());
        super.setBirthDate(person.getBirthDate());
        super.setPhoneNumber(person.getPhoneNumber());
    }

    private void validateUserData(String password) {
        if (password == null || password.isEmpty()) {
            throw new ValidationDataException("Contraseña obligatorio");
        }
    }
}
package co.neoris.service_bank.user_repository.person;

import co.neoris.service_bank.model.person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("person")
public class PersonDAO {
    private Long id;
    private String completeName;
    private Person.Gender gender;
    private LocalDate birthDate;
    private Long identification;
    private String address;
    private Long phoneNumber;
}

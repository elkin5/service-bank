package co.neoris.service_bank.user_repository.person;

import co.neoris.service_bank.model.person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("person")
public class PersonDAO {
    @Id
    @Column("id")
    private Long personId;
    private Long identification;
    private String completeName;
    private Person.Gender gender;
    private LocalDate birthDate;
    private String address;
    private Long phoneNumber;
}

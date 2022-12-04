package co.neoris.service_bank.user_repository.person;


import co.neoris.service_bank.model.person.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDAO toDAO(Person person);

    Person toModel(PersonDAO personDAO);
}

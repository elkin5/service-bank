package co.neoris.service_bank.user_repository.person;


import co.neoris.service_bank.model.person.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDAO toDAO(Person person);

    @Mapping(target = "id", ignore = true)
    Person toModel(PersonDAO personDAO);
}

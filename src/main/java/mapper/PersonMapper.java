package mapper;

import br.com.personmanager.personapi.dto.request.PersonDTO;
import br.com.personmanager.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel (PersonDTO PersonDTO);

    PersonDTO toDTO(Person person);
}

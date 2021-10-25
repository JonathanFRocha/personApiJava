package br.com.personmanager.personapi.service;

import br.com.personmanager.personapi.dto.MessageResponseDTO;
import br.com.personmanager.personapi.dto.request.PersonDTO;
import br.com.personmanager.personapi.entity.Person;
import br.com.personmanager.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.personmanager.personapi.utils.PersonUtils.createFakeDTO;
import static br.com.personmanager.personapi.utils.PersonUtils.createFakeEntity;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDtoThenReturnSuccessMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavePerson = createFakeEntity();

        Mockito.when(personRepository.save(expectedSavePerson)).thenReturn(expectedSavePerson);

        MessageResponseDTO expectedSuccessMessage = MessageResponseDTO
                .builder()
                .message("Created person with ID " + expectedSavePerson.getId())
                .build();

        MessageResponseDTO successMsg = personService.createPerson(personDTO);

        Assertions.assertEquals(expectedSuccessMessage, successMsg);
    }
}

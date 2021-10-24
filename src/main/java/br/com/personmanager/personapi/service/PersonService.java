package br.com.personmanager.personapi.service;

import br.com.personmanager.personapi.dto.MessageResponseDTO;
import br.com.personmanager.personapi.dto.request.PersonDTO;
import br.com.personmanager.personapi.entity.Person;
import br.com.personmanager.personapi.repository.PersonRepository;
import mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        var personToSave = personMapper.toModel(personDTO);


        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}

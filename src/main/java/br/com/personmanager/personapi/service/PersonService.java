package br.com.personmanager.personapi.service;

import br.com.personmanager.personapi.dto.MessageResponseDTO;
import br.com.personmanager.personapi.dto.request.PersonDTO;
import br.com.personmanager.personapi.entity.Person;
import br.com.personmanager.personapi.exception.PersonNotFoundException;
import br.com.personmanager.personapi.repository.PersonRepository;
import mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return createMethodResponse("Created person with ID " + savedPerson.getId());
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person PersonToSave = personMapper.toModel(personDTO);

        var savedPerson = personRepository.save(PersonToSave);
        return createMethodResponse("Person was updated successfully");
    }

    public void Delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private MessageResponseDTO createMethodResponse(String s) {
        return MessageResponseDTO
                .builder()
                .message(s)
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}

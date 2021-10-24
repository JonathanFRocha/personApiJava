package br.com.personmanager.personapi.repository;

import br.com.personmanager.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

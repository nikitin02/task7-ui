package com.example.RestMvcApp.services;

import com.example.RestMvcApp.models.Person;
import com.example.RestMvcApp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
    public List<Person> findByPhoneLike(String phone) {
        return personRepository.findPersonByPhoneContainsIgnoreCase(phone);
    }

    public List<Person> findByLastName(String last_name) {
        return personRepository.findPersonByLast_nameContainsIgnoreCase(last_name);
    }
    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public Person findById(int id) {
        return personRepository.findById(id).orElse(null);
    }
}

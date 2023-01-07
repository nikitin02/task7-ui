package com.example.RestMvcApp.Controllers;


import com.example.RestMvcApp.dto.PersonDTO;
import com.example.RestMvcApp.models.Person;
import com.example.RestMvcApp.services.PersonService;
import com.example.RestMvcApp.util.ErrorResponse;
import com.example.RestMvcApp.util.PersonNotCreatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getPeople() {
        List<PersonDTO> people = personService.findAll().stream().map(this::convertToPersonDTO).toList();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable int id) {
        return convertToPersonDTO(personService.findById(id));
    }

    @GetMapping("/phone={phone}")
    public List<PersonDTO> getPersonByPhone(@PathVariable String phone) {
        return personService.findByPhoneLike(phone).stream().map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/name={last_name}")
    public List<PersonDTO> getPersonByNameLike(@PathVariable("last_name") String name) {
        return personService.findByLastName(name).stream().map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        personService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable int id, @RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        errorsHandler(bindingResult);
        personService.update(id, convertToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        errorsHandler(bindingResult);
        personService.save(convertToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(PersonNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private static void errorsHandler(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append((error.getDefaultMessage())).append("; ");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }
    }

}

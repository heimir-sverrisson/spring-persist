package com.mojotech.persist;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;


@RestController
public class PersonController{
    @Autowired
    private PersonRepository personRepository;

    @ApiOperation(value="Create a new person, must provide first_name and last_name")
    @RequestMapping(method=RequestMethod.POST, value="/persons")
    public String new_person(@RequestBody Person person){
        Person p = personRepository.save(person);
        return String.format("id: %d", p.getId());
    }

    @ApiOperation(value="Update a person, must provide id, first_name, last_name")
    @RequestMapping(method=RequestMethod.PATCH, value="/persons")
    public ResponseEntity<?> update_person(@RequestBody Person person){
        Optional<Person> p = personRepository.findById(person.getId());
        if(!p.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        p.get().setFirstName(person.getFirstName());
        p.get().setMiddleName(person.getMiddleName());
        p.get().setLastName(person.getLastName());
        personRepository.save(p.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value="Return a list of all persons")
    @RequestMapping(method=RequestMethod.GET, value="/persons")
    public List<Person> list_persons(){
        return personRepository.findAll();
    }
}

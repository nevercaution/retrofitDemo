package com.nevercaution.retrofit_demo.controller;

import com.nevercaution.retrofit_demo.model.Person;
import com.nevercaution.retrofit_demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public List<Person> personList() {
        List<Person> personList = personService.getPersonList();
        System.out.println("personList = " + personList);
        return personList;
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Person getPerson(@RequestParam(value = "name") String name) {
        System.out.println("name = " + name);
        return personService.getPerson(name);
    }
}

package com.nevercaution.retrofit_demo.service;

import com.nevercaution.retrofit_demo.model.Person;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private List<Person> personList;

    @PostConstruct
    public void init() {
        personList = new ArrayList<>();
        personList.add(new Person("teddy", 10));
        personList.add(new Person("bono", 10));
        personList.add(new Person("land", 10));
        personList.add(new Person("eden", 10));
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public Person getPerson(String name) {
        for (Person person : personList) {
            if (name.equals(person.getName())) {
                return person;
            }
        }
        return null;
    }
}

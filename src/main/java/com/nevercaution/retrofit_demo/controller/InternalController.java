package com.nevercaution.retrofit_demo.controller;

import com.nevercaution.retrofit_demo.model.Person;
import com.nevercaution.retrofit_demo.service.InternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/internal")
public class InternalController {

    @Autowired
    private InternalApiService internalApiService;

    @GetMapping("/persons")
    public List<Person> getPersonListSync() {
        System.out.println("DemoController.getPersonList");
        return internalApiService.getInternalPersonList().get();
    }

    @GetMapping("/persons/async")
    public Mono<List<Person>> getPersonListAsync() {
        System.out.println("DemoController.getPersonListAsync");
        return internalApiService.getInternalPersonListAsync()
                .onErrorResume(error -> Mono.just(new ArrayList<>()));
    }

    @PostMapping("/person")
    public Person getPerson(@RequestParam("name") String name) {
        System.out.println("getPerson1 name = " + name);
        return internalApiService.getInternalPerson(name);
    }

    @PostMapping("/person/async")
    public Mono<Person> getPersonAsync(@RequestParam("name") String name) {
        System.out.println("InternalController.getPersonAsync");
        return internalApiService.getInternalPersonAsync(name)
                .onErrorResume(error -> {
                    System.out.println("getPersonAsync error = " + error);
                   return Mono.empty();
                });
    }
}

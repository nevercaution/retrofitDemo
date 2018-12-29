package com.nevercaution.retrofit_demo.service;

import com.nevercaution.retrofit_demo.model.Person;
import com.nevercaution.retrofit_demo.util.CustomCallback;
import com.nevercaution.retrofit_demo.util.RequestUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import retrofit2.Call;
import retrofit2.Response;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InternalApiService {

    private PersonAPI personAPI;

    @PostConstruct
    public void init() {
        personAPI = RequestUtil.createService(PersonAPI.class);
    }

    public Optional<List<Person>> getInternalPersonList() {
        Optional<List<Person>> people = RequestUtil.requestSync(personAPI.getPersonList());
        return people;
    }

    public Mono<List<Person>> getInternalPersonListAsync() {
        System.out.println("InternalApiService.getInternalPersonListAsync start");
        Mono<List<Person>> mono = Mono.create(sink -> {
            RequestUtil.requestAsync(personAPI.getPersonList(), new CustomCallback<List<Person>>() {
                @Override
                public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                    if (!response.isSuccessful()) {
                        System.out.println("response = " + response);
                        sink.error(new Exception("response is empty"));
                        return;
                    }

                    sink.success(Objects.requireNonNull(response.body()));
                }
            });
        });
        System.out.println("InternalApiService.getInternalPersonListAsync end");
        return mono;
    }


    public Person getInternalPerson(String name) {
        Optional<Person> person = RequestUtil.requestSync(personAPI.getPerson(name));
        return person.get();
    }

    public Mono<Person> getInternalPersonAsync(String name) {
        System.out.println("InternalApiService.getInternalPersonAsync start");
        System.out.println("name = " + name);
        Mono<Person> mono = Mono.create(sink -> {
            RequestUtil.requestAsync(personAPI.getPerson(name), new CustomCallback<Person>() {
                @Override
                public void onResponse(Call<Person> call, Response<Person> response) {
                    if (!response.isSuccessful()) {
                        sink.error(new IllegalAccessError("invalid parameter"));
                        return;
                    }
                    sink.success(Objects.requireNonNull(response.body()));
                }

                @Override
                public void onFailure(Call<Person> call, Throwable t) {
                    System.out.println("InternalApiService.onFailure, " + t);
                    sink.error(t);
                }
            });
        });
        System.out.println("InternalApiService.getInternalPersonAsync end");
        return mono;
    }
}

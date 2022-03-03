package com.example.application.views.people;

import dev.hilla.Endpoint;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class PeopleEndpoint {

    // We'll use a simple list to hold data
    private List<Person> people = new ArrayList<>();

    public PeopleEndpoint() {
        // Add one person so we can see that everything works
        people.add(new Person("Jane", "Doe"));
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person addPerson(Person person) {
        people.add(person);
        return person;
    }
}

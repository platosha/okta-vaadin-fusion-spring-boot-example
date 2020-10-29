package com.example.application.views.people;

import com.example.application.views.people.Person;
import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;

import java.util.ArrayList;
import java.util.List;

@Endpoint
@AnonymousAllowed
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

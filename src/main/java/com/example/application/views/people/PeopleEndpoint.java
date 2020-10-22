package com.example.application.views.people;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;

/**
 * The endpoint for the client-side view.
 */
@Endpoint
@AnonymousAllowed
public class PeopleEndpoint {

  // Instead of a database, we'll use a simple list to hold data
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

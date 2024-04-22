package com.ethswitch.tenant.service;

import com.ethswitch.tenant.crud.service.CrudService;
import com.ethswitch.tenant.domain.Person;

import java.util.Optional;

public interface PersonService extends CrudService<Person, Integer> {

    Optional<Person> getPersonByEmail(String email);
}

package com.ethswitch.tenant.service;


import com.ethswitch.tenant.crud.service.CrudServiceImpl;
import com.ethswitch.tenant.domain.Person;
import com.ethswitch.tenant.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PersonServiceImpl extends CrudServiceImpl<Person, Integer, PersonRepository> implements PersonService {

    public PersonServiceImpl(PersonRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Person> getPersonByEmail(String email) {
        return repository.findFirstByEmail(email);
    }
}

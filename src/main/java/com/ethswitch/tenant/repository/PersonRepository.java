package com.ethswitch.tenant.repository;

import com.ethswitch.tenant.crud.repository.CustomCrudRepository;
import com.ethswitch.tenant.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CustomCrudRepository<Person, Integer> {

    Optional<Person> findFirstByEmail(String email);

}

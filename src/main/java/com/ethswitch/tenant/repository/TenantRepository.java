package com.ethswitch.tenant.repository;

import com.ethswitch.tenant.crud.repository.CustomCrudRepository;
import com.ethswitch.tenant.domain.Tenant;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends CustomCrudRepository<Tenant, Integer> {

    Optional<Tenant> findByEmail(String email);

}

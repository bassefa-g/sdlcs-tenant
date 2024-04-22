package com.ethswitch.tenant.repository;

import com.ethswitch.tenant.constants.LookupTypeEnum;
import com.ethswitch.tenant.crud.repository.CustomCrudRepository;
import com.ethswitch.tenant.domain.Lookup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LookupRepository extends CustomCrudRepository<Lookup, Integer> {

    List<Lookup> findAllByLookupTypeAndDmlFlagNot(LookupTypeEnum lookupType, int dmlFlag);
}

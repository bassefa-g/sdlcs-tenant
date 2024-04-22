package com.ethswitch.tenant.service;


import com.ethswitch.tenant.constants.LookupTypeEnum;
import com.ethswitch.tenant.crud.constants.DmlFlagEnum;
import com.ethswitch.tenant.crud.service.CrudServiceImpl;
import com.ethswitch.tenant.domain.Lookup;
import com.ethswitch.tenant.repository.LookupRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LookupServiceImpl extends CrudServiceImpl<Lookup, Integer, LookupRepository> implements LookupService {

    public LookupServiceImpl(LookupRepository repository) {
        super(repository);
    }

    @Override
    public Lookup getLookup(String uuid) {
        return repository.findByUuid(uuid).orElseThrow(
                () -> new EntityNotFoundException("Lookup with id " + uuid + " doesn't exists")
        );
    }

    @Override
    public List<Lookup> getByType(LookupTypeEnum lookupType) {
        return repository.findAllByLookupTypeAndDmlFlagNot(lookupType, DmlFlagEnum.DELETED.getValue());
    }
}

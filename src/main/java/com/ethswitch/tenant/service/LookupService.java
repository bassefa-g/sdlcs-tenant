package com.ethswitch.tenant.service;

import com.ethswitch.tenant.constants.LookupTypeEnum;
import com.ethswitch.tenant.crud.service.CrudService;
import com.ethswitch.tenant.domain.Lookup;

import java.util.List;

public interface LookupService extends CrudService<Lookup, Integer> {

    Lookup getLookup(String uuid);

    List<Lookup> getByType(LookupTypeEnum lookupType);
}

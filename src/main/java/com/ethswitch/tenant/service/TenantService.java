package com.ethswitch.tenant.service;

import com.ethswitch.tenant.crud.service.CrudService;
import com.ethswitch.tenant.domain.Tenant;
import com.ethswitch.tenant.dto.ConfigurationDto;
import com.ethswitch.tenant.dto.PersonDto;
import com.ethswitch.tenant.dto.TenantDto;
import com.ethswitch.tenant.dto.TenantResponse;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.Set;

public interface TenantService extends CrudService<Tenant, Integer> {

    TenantResponse get(String uuid);

    TenantResponse save(TenantDto dto);

    TenantResponse update(String uuid, TenantDto dto);
    TenantResponse update(String uuid, JsonPatch patch);

    TenantResponse updateContactPerson(String uuid, Set<PersonDto> dtoList);

    TenantResponse setProperties(String uuid, Set<ConfigurationDto> dtoList);
}

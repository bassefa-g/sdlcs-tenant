package com.ethswitch.tenant.service;


import com.ethswitch.tenant.crud.exception.CustomRuntimeException;
import com.ethswitch.tenant.crud.service.CrudServiceImpl;
import com.ethswitch.tenant.domain.Configuration;
import com.ethswitch.tenant.domain.Person;
import com.ethswitch.tenant.domain.Tenant;
import com.ethswitch.tenant.dto.ConfigurationDto;
import com.ethswitch.tenant.dto.PersonDto;
import com.ethswitch.tenant.dto.TenantDto;
import com.ethswitch.tenant.dto.TenantResponse;
import com.ethswitch.tenant.repository.TenantRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
public class TenantServiceImpl extends CrudServiceImpl<Tenant, Integer, TenantRepository> implements TenantService {

    @Autowired
    ModelMapper mapper;

    ObjectMapper objectMapper;

    public TenantServiceImpl(TenantRepository repository) {
        super(repository);
        objectMapper = new ObjectMapper();
    }


    @Override
    public TenantResponse get(String uuid) {
        var object = findByUuid(uuid);
        return map(object);
    }

    @Override
    public TenantResponse save(TenantDto dto) {
        var object = mapper.map(dto, Tenant.class);
        validate(dto, object);
        setDetails(dto, object);
        object = repository.save(object);
        return map(object);
    }

    @Override
    public TenantResponse update(String uuid, TenantDto dto) {
        var object = findByUuid(uuid);
        validate(dto, object);
        mapper.map(dto, object);
        setDetails(dto, object);
        object = repository.save(object);
        return map(object);
    }

    @Override
    public TenantResponse update(String uuid, JsonPatch patch) {
        var object = findByUuid(uuid);
        try {
            var objectPatched = applyPatchToCustomer(patch, object);
            object = repository.save(objectPatched);

        } catch (JsonPatchException | JsonProcessingException e) {
            throw new CustomRuntimeException("Invalid patch request");
        }
        return map(object);
    }

    @Override
    public TenantResponse updateContactPerson(String uuid, Set<PersonDto> dtoList) {
        var object = findByUuid(uuid);
        setContactPerson(dtoList, object);
        object = repository.save(object);
        return map(object);
    }

    @Override
    public TenantResponse setProperties(String uuid, Set<ConfigurationDto> dtoList) {
        var object = findByUuid(uuid);
        setConfigurations(dtoList, object);
        object = repository.save(object);
        return map(object);
    }

    private void validate(TenantDto dto, Tenant object) {
        var old = repository.findByEmail(dto.getEmail());
        if (old.isPresent() && Objects.equals(old.get().getId(), object.getId())) {
            throw new CustomRuntimeException("Tenant already exists. Email: " + dto.getEmail());
        }
    }
    private Tenant applyPatchToCustomer(
            JsonPatch patch, Tenant object) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(object, JsonNode.class));
        return objectMapper.treeToValue(patched, Tenant.class);
    }

    private TenantResponse map(Tenant object) {
        return mapper.map(object, TenantResponse.class);
    }

    private void setDetails(TenantDto dto, Tenant object) {
        setContactPerson(dto.getContactPersons(), object);
        setConfigurations(dto.getConfigurations(), object);
    }


    private void setContactPerson(Set<PersonDto> dtoList, Tenant object) {
        object.getContactPersons().clear();
        dtoList.forEach(personDto ->
                object.getContactPersons().add(mapper.map(personDto, Person.class)));
    }

    private void setConfigurations(Set<ConfigurationDto> configurationDtoList, Tenant object) {
        object.getConfigurations().clear();
        configurationDtoList.forEach(conf ->
                object.getConfigurations().add(mapper.map(conf, Configuration.class)));
    }
}

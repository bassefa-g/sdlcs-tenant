package com.ethswitch.tenant.controller;


import com.ethswitch.tenant.constants.SdlsUrls;
import com.ethswitch.tenant.crud.constants.Constants;
import com.ethswitch.tenant.crud.representaion.ApiResponse;
import com.ethswitch.tenant.dto.ConfigurationDto;
import com.ethswitch.tenant.dto.CustomPageRequest;
import com.ethswitch.tenant.dto.PersonDto;
import com.ethswitch.tenant.dto.TenantDto;
import com.ethswitch.tenant.dto.TenantResponse;
import com.ethswitch.tenant.service.TenantService;
import com.github.fge.jsonpatch.JsonPatch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(SdlsUrls.ENTRY_POINT + SdlsUrls.TENANT)
public class TenantController {

    @Autowired
    ModelMapper mapper;

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public Page<TenantResponse> getAllTenants(CustomPageRequest pageRequest) {
        var pageable = PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
        var users = tenantService.findAll(pageable);

        List<TenantResponse> userResponses = users.getContent().stream()
                .map(obj -> mapper.map(obj, TenantResponse.class))
                .toList();

        return new PageImpl<>(userResponses, pageable, users.getTotalElements());
    }

    @GetMapping(SdlsUrls.GET_BY_UUID)
    public ResponseEntity<TenantResponse> getAllTenantById(@PathVariable("uud") String uuid) {
        var response = tenantService.get(uuid);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TenantResponse> createTenant(@RequestBody TenantDto dto) {

        var response = tenantService.save(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<TenantResponse> updateTenant(@PathVariable String uuid, @RequestBody TenantDto dto) {

        var reponse = tenantService.update(uuid, dto);

        return ResponseEntity.ok().body(reponse);
    }

    @PatchMapping(path = "/{uuid}")
    public ResponseEntity<TenantResponse> patchUpdateTenant(@PathVariable String uuid, @RequestBody JsonPatch patch) {
       var response = tenantService.update(uuid, patch);
       return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{uuid}/contact-person")
    public ResponseEntity<TenantResponse> updateContactPerson(@PathVariable String uuid, @RequestBody List<PersonDto> dtoList) {

        var response = tenantService.updateContactPerson(uuid, new HashSet<>(dtoList));

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{uuid}/properties")
    public ResponseEntity<TenantResponse> setProperties(@PathVariable String uuid, @RequestBody List<ConfigurationDto> dtoList) {

        var response = tenantService.setProperties(uuid, new HashSet<>(dtoList));

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<ApiResponse> deleteTenant(@PathVariable(name = "uuid") String uuid) {
        tenantService.softDelete(uuid);
        ApiResponse apiResponse = new ApiResponse(Constants.SUCCESS, "Tenant deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}

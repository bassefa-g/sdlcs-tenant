package com.ethswitch.tenant.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TenantDto {

    private String name;
    private String businessType;
    private String businessRegistrationNumber;
    private String taxIdentificationNumber;
    private String numberOfEmployees;
    private boolean agreedToLegalAgreements;
    private boolean agreedTermsAndConditions;
    private boolean readPrivacyPolicy;
    private String email;

    private int status;

    private Set<PersonDto> contactPersons = new HashSet<>();

    private Set<ConfigurationDto> configurations = new HashSet<>();
}
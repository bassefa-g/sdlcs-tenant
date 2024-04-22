package com.ethswitch.tenant.domain;

import com.ethswitch.tenant.crud.domain.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Tenant extends AbstractEntity {

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tenant_contact_persons", joinColumns = {
            @JoinColumn(name = "tenant_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "contact_person_id", referencedColumnName = "id") })
    private Set<Person> contactPersons = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tenant_contact_configurations", joinColumns = {
            @JoinColumn(name = "tenant_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "configuration_id", referencedColumnName = "id") })
    private Set<Configuration> configurations = new HashSet<>();
}
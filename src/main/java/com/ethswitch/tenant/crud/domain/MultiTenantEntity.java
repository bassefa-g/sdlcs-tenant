package com.ethswitch.tenant.crud.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class MultiTenantEntity extends AbstractEntity {

    private Integer organizationId;
}

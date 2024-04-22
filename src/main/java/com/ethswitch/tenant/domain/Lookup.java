package com.ethswitch.tenant.domain;

import com.ethswitch.tenant.constants.LookupTypeEnum;
import com.ethswitch.tenant.crud.domain.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@Entity
@Table(name = "lookup", uniqueConstraints = {@UniqueConstraint(columnNames = {"lookupType", "name"})})
public class Lookup extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = -4571337186585688860L;

    @Enumerated(EnumType.STRING)
    private LookupTypeEnum lookupType;

    private String name;

    private String description;

    @Override
    public String toString() {
        return name;
    }
}

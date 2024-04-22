package com.ethswitch.tenant.domain;

import com.ethswitch.tenant.crud.domain.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "configuration")
public class Configuration extends AbstractEntity {

    @Column(name = "app_key")
    private String key;
    @Column(name = "app_value")
    private String value;
}

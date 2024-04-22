package com.ethswitch.tenant.crud.constants;

import lombok.Getter;

@Getter
public enum DmlFlagEnum {

    CREATED(1),
    UPDATED(2),
    DELETED(3);

    private final int value;

    DmlFlagEnum(int value) {
        this.value = value;
    }
}

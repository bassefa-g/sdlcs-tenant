package com.ethswitch.tenant.dto;

import com.ethswitch.tenant.constants.LookupTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LookupDto {

    private String uuid;

    @Enumerated(EnumType.STRING)
    private LookupTypeEnum lookupType;

    private String value;

    private String description;
}

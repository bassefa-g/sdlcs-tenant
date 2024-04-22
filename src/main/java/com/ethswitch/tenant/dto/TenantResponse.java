package com.ethswitch.tenant.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TenantResponse extends TenantDto {

    private String uuid;

    private int status;
}
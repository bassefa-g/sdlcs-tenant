package com.ethswitch.tenant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomPageRequest {

    private int pageNumber = 1;
    private int pageSize = 10;
}

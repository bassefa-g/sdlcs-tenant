package com.ethswitch.tenant.conf;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SdlcsTenantConf {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

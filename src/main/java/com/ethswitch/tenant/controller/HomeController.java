package com.ethswitch.tenant.controller;

import com.ethswitch.tenant.constants.SdlsUrls;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(SdlsUrls.ENTRY_POINT + SdlsUrls.HOME)
public class HomeController {

    @GetMapping
    public String home() {
        return "hello";
    }

}
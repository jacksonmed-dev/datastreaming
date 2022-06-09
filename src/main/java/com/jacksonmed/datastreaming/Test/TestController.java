package com.jacksonmed.datastreaming.Test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String health() {
        return "Hello Jackson Med!!! We have an API!!";
    }
}
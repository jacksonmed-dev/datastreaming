package com.example.jacksonmed.controller;

import com.example.jacksonmed.model.Test;
import com.example.jacksonmed.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping("/test/{uniqueId}")
    public Test get(@PathVariable String uniqueId) throws SQLException  {
        return testService.retrieveTest(uniqueId);
    }

    @PostMapping("/test")
    @ResponseBody
    public void insert(@RequestBody Test test) throws SQLException {
        testService.insertTest(test);
    }
}

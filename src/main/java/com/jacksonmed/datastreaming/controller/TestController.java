package com.jacksonmed.datastreaming.controller;

import com.jacksonmed.datastreaming.model.Patient;
import com.jacksonmed.datastreaming.model.Test;
import com.jacksonmed.datastreaming.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping("/")
    public String health() {
        return "Hello Jackson Med!!! We have an API!!";
    }

    @GetMapping("/test1/{name}")
    public String test(@PathVariable String name) {
        //set up java object to save to cassandra
        //Use DriverConfigLoader to load your configuration file
//        DriverConfigLoader loader = DriverConfigLoader.fromClasspath("application.conf");
//        try (CqlSession session = CqlSession.builder()
//                .withConfigLoader(loader)
//                .build()) {
//
//            ResultSet rs = session.execute("select * from jms_keyspace1.test");
//            Row row = rs.one();
//            System.out.println(row);
        Test test = new Test();
        test.setName(name);
//            testService.getTestData(test.getName());
        testService.insertTestData(test);
        return "This is a test of the /test api endpoint";
    }

    @GetMapping("/test2/{name}")
    public Test get(@PathVariable String name) {
        return testService.getTestData(name);
    }


}


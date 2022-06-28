package com.jacksonmed.datastreaming.service;

import com.jacksonmed.datastreaming.dao.TestDaoImpl;
import com.jacksonmed.datastreaming.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TestService")
public class TestService {
    @Autowired
    TestDaoImpl testDao;

    public Test getTestData(String name) {
        return testDao.retrieveTestData(name);
    }

    public void insertTestData(Test test){
        testDao.insertTestData(test);
    }

}

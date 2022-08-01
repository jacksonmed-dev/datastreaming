package com.example.jacksonmed.service;

import com.example.jacksonmed.dao.TestDao;
import com.example.jacksonmed.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("TestService")
public class TestService {
    @Autowired
    TestDao testDao;
    public Test retrieveTest(String uniqueId) throws SQLException  {
        return testDao.retrieveTest(uniqueId);
    }
    public void insertTest(Test test) throws SQLException {
        testDao.insertTest(test);
    }
}

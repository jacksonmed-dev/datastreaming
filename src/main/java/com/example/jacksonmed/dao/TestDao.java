package com.example.jacksonmed.dao;

import com.example.jacksonmed.model.Test;

import java.sql.SQLException;

public interface TestDao {
    Test retrieveTest(String uniqueId) throws SQLException;
    void insertTest(Test test) throws SQLException;
}

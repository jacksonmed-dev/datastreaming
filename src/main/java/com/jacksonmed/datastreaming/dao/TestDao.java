package com.jacksonmed.datastreaming.dao;

import com.jacksonmed.datastreaming.model.Test;

public interface TestDao {
    void insertTestData(Test testData);
    Test retrieveTestData(String name);
}

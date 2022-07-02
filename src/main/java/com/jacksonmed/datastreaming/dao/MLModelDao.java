package com.jacksonmed.datastreaming.dao;

import com.jacksonmed.datastreaming.model.MLModel;

public interface MLModelDao {
    void insertMLModel(MLModel mlModel);
    MLModel retrieveMLModel(String uniqueId);
}

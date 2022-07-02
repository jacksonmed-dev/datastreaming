package com.jacksonmed.datastreaming.service;

import com.jacksonmed.datastreaming.dao.MLModelDaoImpl;
import com.jacksonmed.datastreaming.dao.PatientDao;
import com.jacksonmed.datastreaming.model.MLModel;
import com.jacksonmed.datastreaming.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MLModelService")
public class MLModelService {
    @Autowired
    MLModelDaoImpl mlModelDao;

    public MLModel retrieveMLModel(String uniqueId) {
        return mlModelDao.retrieveMLModel(uniqueId);
    }

    public void insertMLModel(MLModel mlModel){
        mlModelDao.insertMLModel(mlModel);
    }
}

package com.jacksonmed.datastreaming.controller;

import com.jacksonmed.datastreaming.model.MLModel;
import com.jacksonmed.datastreaming.model.Patient;
import com.jacksonmed.datastreaming.service.MLModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service("MLModelController")
public class MLModelController {
    @Autowired
    MLModelService mlModelService;

//    @RequestMapping(value = "/patient", method = RequestMethod.GET, produces = "application/json")

    @PostMapping("/ml_model")
    public void insert(@RequestBody MLModel mlModel) {
        mlModelService.insertMLModel(mlModel);
    }

    @GetMapping("/ml_model")
    public MLModel get(String uniqueId) {
        return mlModelService.retrieveMLModel(uniqueId);
    }
}

package com.example.fiscos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fiscos.dto.classification.ClassificationDTO;
import com.example.fiscos.service.ClassificationService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/classification")
public class ClassificationController {
    
    ClassificationService classificationService;

    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @GetMapping()
    public List<ClassificationDTO> getAllClassifications() {
        return classificationService.getAllClassifications();
    }

    @GetMapping("/{id}")
    public ClassificationDTO getClassificationById(@PathVariable Long id) {
        return classificationService.getClassificationById(id);
    }
    
    
}

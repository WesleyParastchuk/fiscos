package com.example.fiscos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.fiscos.model.Classification;
import com.example.fiscos.model.mongo.ClassificationBackup;

@Component
public class ClassificationMapper {

    public ClassificationBackup toBackup(Classification classification) {
        ClassificationBackup classificationBackup = new ClassificationBackup();
        classificationBackup.setName(classification.getName());
        classificationBackup.setShortName(classification.getShortName());
        classificationBackup.setDescription(classification.getDescription());

        if (classification.getParent() != null) {
            classificationBackup.setParent(toBackup(classification.getParent()));
        }

        return classificationBackup;
    }

    public List<ClassificationBackup> toBackupList(List<Classification> classifications) {
        return classifications.stream()
                .map(this::toBackup)
                .collect(Collectors.toList());
    }
}

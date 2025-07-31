package com.example.fiscos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.fiscos.dto.classification.ClassificationDTO;
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

    public ClassificationDTO toDto(Classification classification) {
        ClassificationDTO dto = new ClassificationDTO();
        dto.setId(classification.getId());
        dto.setName(classification.getName());
        dto.setDescription(classification.getDescription());
        dto.setActive(classification.isActive());

        if (classification.getParent() != null) {
            dto.setParent(toDto(classification.getParent()));
        }

        return dto;
    }

    public List<ClassificationDTO> toDtoList(List<Classification> classifications) {
        return classifications.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

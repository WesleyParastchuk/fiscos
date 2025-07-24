package com.example.fiscos.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.fiscos.model.Classification;
import com.example.fiscos.repository.ClassificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ClassificationService {

    private final ClassificationRepository classificationRepository;

    public ClassificationService(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    public List<Map<String, Object>> getClassificationTree() {
        List<Classification> all = classificationRepository.findAll();

        Map<Long, List<Classification>> childrenMap = new HashMap<>();
        for (Classification c : all) {
            if (c.getParent() != null) {
                childrenMap.computeIfAbsent(c.getParent().getId(), k -> new ArrayList<>()).add(c);
            }
        }

        java.util.function.Function<Classification, Map<String, Object>> buildNode = new java.util.function.Function<>() {
            @Override
            public Map<String, Object> apply(Classification c) {
                Map<String, Object> node = new HashMap<>();
                node.put("id", c.getId());
                node.put("name", c.getName());

                List<Classification> children = childrenMap.getOrDefault(c.getId(), Collections.emptyList())
                        .stream()
                        .filter(Classification::isActive)
                        .toList();

                if (!children.isEmpty()) {
                    List<Map<String, Object>> childNodes = new ArrayList<>();
                    for (Classification child : children) {
                        childNodes.add(this.apply(child));
                    }
                    node.put("children", childNodes);
                }
                return node;
            }
        };

        // Filtra raízes ativas e que não sejam "pais vazios"
        return all.stream()
                .filter(c -> c.getParent() == null && c.isActive())
                .filter(c -> {
                    // Remove possíveis "pais vazios" sem filhos ativos
                    List<Classification> children = childrenMap.getOrDefault(c.getId(), Collections.emptyList())
                            .stream()
                            .filter(Classification::isActive)
                            .toList();
                    return !children.isEmpty() || (children.isEmpty() && c.getName() != null && !c.getName().isBlank());
                })
                .map(buildNode)
                .toList();
    }

    public String getClassificationTreeJson() {
        List<Map<String, Object>> tree = getClassificationTree();

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(tree);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter árvore para JSON", e);
        }
    }

}

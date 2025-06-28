package com.example.fiscos.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class VersionControl {

    private Integer initialVersion = 1;

    private static final Path VERSION_DIR = Paths.get("backups", "_VERSION");
    private static final Path VERSION_FILE = VERSION_DIR.resolve("version.json");

    @Autowired
    private ObjectMapper objectMapper;

    public Integer getVersion() {
        Integer savedVersion = getSavedVersion();
        if (savedVersion != null) {
            System.out.println("Current version: " + savedVersion);
            return savedVersion;
        }

        this.createVersionFile();
        return this.initialVersion;
    }

    private void createVersionFile() {
        try {
            writeVersionFile(initialVersion);
        } catch (IOException e) {
            System.err.println("Error creating version file: " + e.getMessage());
        }
    }

    public Integer incrementVersion() {
        Integer currentVersion = getSavedVersion();
        Integer newVersion = (currentVersion != null) ? currentVersion + 1 : initialVersion;

        try {
            writeVersionFile(newVersion);
            return newVersion;
        } catch (IOException e) {
            return null;
        }
    }

    private void writeVersionFile(Integer versionValue) throws IOException {
        if (!Files.exists(VERSION_DIR)) {
            Files.createDirectories(VERSION_DIR);
        }

        ObjectNode versionNode = objectMapper.createObjectNode();
        versionNode.put("databaseVersion", versionValue);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(VERSION_FILE.toFile(), versionNode);
    }

    private Integer getSavedVersion() {
        try {
            if (Files.exists(VERSION_FILE)) {
                JsonNode root = objectMapper.readTree(VERSION_FILE.toFile());
                if (root.has("databaseVersion") && root.get("databaseVersion").isInt()) {
                    return root.get("databaseVersion").asInt();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading version file: " + e.getMessage());
        }

        return null;
    }

}

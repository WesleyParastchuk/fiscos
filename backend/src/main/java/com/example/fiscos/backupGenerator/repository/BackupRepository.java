package com.example.fiscos.backupGenerator.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.fiscos.config.VersionControl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BackupRepository {

    @Autowired
    private VersionControl versionControl;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Path BACKUP_ROOT = Paths.get("backups");

    public void saveBackup(String entityName, JsonNode payload) throws IOException {
        int version = versionControl.getVersion();

        String timestamp = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH-mm-ss'Z'")
            .withZone(Instant.now().atZone(DateTimeFormatter.ISO_INSTANT.getZone()).getZone())
            .format(Instant.now());

        Path targetFile = BACKUP_ROOT
            .resolve("_" + version)
            .resolve(entityName)
            .resolve(timestamp + ".json");

        Files.createDirectories(targetFile.getParent());

        objectMapper
            .writerWithDefaultPrettyPrinter()
            .writeValue(targetFile.toFile(), payload);
    }
}

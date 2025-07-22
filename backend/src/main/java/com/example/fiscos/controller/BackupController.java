package com.example.fiscos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fiscos.service.BackupService;

@RestController
@RequestMapping("/backup")
public class BackupController {

    private final BackupService backupService;

    public BackupController(BackupService backupService) {
        this.backupService = backupService;
    }

    @GetMapping("/backup")
    public ResponseEntity<String> getBackup() {
        return ResponseEntity.ok(backupService.getBackupInString());
    }

    @PostMapping("/backup")
    public ResponseEntity<String> createBackup() {
        backupService.backupPostgresToMongo();

        return ResponseEntity.ok("Backup created successfully");
    }
}
package com.example.fiscos.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.fiscos.model.mongo.ProcessedProductBackup;

@Repository
public interface ProcessedProductBackupRepository extends MongoRepository<ProcessedProductBackup, String> {
}

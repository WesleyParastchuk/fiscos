package com.example.fiscos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.fiscos.mapper.ClassificationMapper;
import com.example.fiscos.mapper.ProcessedProductMapper;
import com.example.fiscos.mapper.RawProductMapper;
import com.example.fiscos.model.ProcessedProduct;
import com.example.fiscos.model.ProductClassification;
import com.example.fiscos.model.mongo.ClassificationBackup;
import com.example.fiscos.model.mongo.ProcessedProductBackup;
import com.example.fiscos.model.mongo.RawProductBackup;
import com.example.fiscos.repository.ProcessedProductRepository;
import com.example.fiscos.repository.mongo.ProcessedProductBackupRepository;

@Service
public class BackupService {

    private final ProcessedProductBackupRepository backupRepo;
    private final ProcessedProductRepository repo;
    private final ProcessedProductMapper processedProductMapper;
    private final RawProductMapper rawProductMapper;
    private final ClassificationMapper classificationMapper;

    public BackupService(ProcessedProductBackupRepository processedProductBackupRepository,
            ProcessedProductRepository processedProductRepository,
            ProcessedProductMapper processedProductMapper,
            RawProductMapper rawProductMapper,
            ClassificationMapper classificationMapper) {
        this.backupRepo = processedProductBackupRepository;
        this.repo = processedProductRepository;
        this.processedProductMapper = processedProductMapper;
        this.rawProductMapper = rawProductMapper;
        this.classificationMapper = classificationMapper;
    }

    public String getBackupInString() {
        List<ProcessedProductBackup> backups = backupRepo.findAll();
        return backups.stream()
            .map(backup -> {
                StringBuilder sb = new StringBuilder();
                sb.append("{");                sb.append("\"creationDate\":").append("\"").append(backup.getCreationDate()).append("\"").append(",");
                sb.append("\"rawProducts\":").append(backup.getRawProducts() != null ? backup.getRawProducts().toString() : "null").append(",");
                sb.append("\"classifications\":").append(backup.getClassifications() != null ? backup.getClassifications().toString() : "null");
                sb.append("}");
                return sb.toString();
            })
            .collect(Collectors.joining(", ", "[", "]"));
    }

    public void backupPostgresToMongo() {
        String currentDateTimestamp = String.valueOf(System.currentTimeMillis());
        List<ProcessedProduct> products = repo.findAll();
        for(ProcessedProduct product : products) {
            List<RawProductBackup> rawProducts = rawProductMapper.toBackupList(product.getRawProducts());
            List<ProductClassification> productClassifications = product.getProductClassifications();
            List<ClassificationBackup> classifications = classificationMapper.toBackupList(
                productClassifications.stream()
                    .map(ProductClassification::getClassification)
                    .collect(Collectors.toList())
            );

            ProcessedProductBackup backup = processedProductMapper.toBackup(product);
            backup.setCreationDate(currentDateTimestamp);
            backup.setRawProducts(rawProducts);
            backup.setClassifications(classifications);

            backupRepo.save(backup);
        }

    }

}

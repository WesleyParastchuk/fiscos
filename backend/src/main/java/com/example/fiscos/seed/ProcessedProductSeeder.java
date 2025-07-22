package com.example.fiscos.seed;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.fiscos.mapper.ProcessedProductMapper;
import com.example.fiscos.mapper.RawProductMapper;
import com.example.fiscos.model.Classification;
import com.example.fiscos.model.ProcessedProduct;
import com.example.fiscos.model.ProductClassification;
import com.example.fiscos.model.RawProduct;
import com.example.fiscos.model.mongo.ProcessedProductBackup;
import com.example.fiscos.model.mongo.RawProductBackup;
import com.example.fiscos.repository.ClassificationRepository;
import com.example.fiscos.repository.ProcessedProductRepository;
import com.example.fiscos.repository.ProductClassificationRepository;
import com.example.fiscos.repository.RawProductRepository;
import com.example.fiscos.repository.mongo.ProcessedProductBackupRepository;
import com.example.fiscos.service.ProcessedProductService;

@Configuration
public class ProcessedProductSeeder {

    private final ClassificationSeeder classificationSeeder;
    
    private final ProcessedProductRepository processedProductRepository;
    private final ClassificationRepository classificationRepository;
    private final ProductClassificationRepository productClassificationRepository;
    private final RawProductRepository rawProductRepository;
    
    private final ProcessedProductBackupRepository processedProductBackupRepository;

    private final ProcessedProductMapper processedProductMapper;
    private final RawProductMapper rawProductMapper;

    public ProcessedProductSeeder(ProcessedProductRepository processedProductRepository,
            ClassificationSeeder classificationSeeder, ClassificationRepository classificationRepository,
            ProcessedProductBackupRepository processedProductBackupRepository, ProductClassificationRepository productClassificationRepository,
            ProcessedProductMapper processedProductMapper, RawProductMapper rawProductMapper, RawProductRepository rawProductRepository) {
        this.processedProductRepository = processedProductRepository;
        this.classificationSeeder = classificationSeeder;
        this.classificationRepository = classificationRepository;
        this.productClassificationRepository = productClassificationRepository;
        this.processedProductBackupRepository = processedProductBackupRepository;
        this.processedProductMapper = processedProductMapper;
        this.rawProductMapper = rawProductMapper;
        this.rawProductRepository = rawProductRepository;
    }

    @Bean
    CommandLineRunner initProcessedProducts() {
        return args -> {
            if (processedProductRepository.count() == 0) {
                classificationSeeder.seed(classificationRepository);
                
                List<ProcessedProductBackup> list = processedProductBackupRepository.findAll();

                list.forEach(backup -> {
                    ProcessedProduct processedProduct = processedProductMapper.toEntity(backup);
                    List<RawProduct> rawProducts = rawProductMapper.toEntityListFromBackup(backup.getRawProducts());
                    processedProduct.setRawProducts(rawProducts);
                    
                    List<ProductClassification> productClassifications = backup.getClassifications().stream()
                            .map(classificationBackup -> {
                                Classification classification = classificationRepository.findByNameAndShortNameAndDescription(
                                        classificationBackup.getName(),
                                        classificationBackup.getShortName(),
                                        classificationBackup.getDescription()
                                );
                                return new ProductClassification(processedProduct, classification);
                            }).collect(Collectors.toList());
                    
                    processedProduct.setProductClassifications(productClassifications);
                    
                    processedProductRepository.save(processedProduct);
                });

                System.out.println(">>> APOS APOS APOS criados com sucesso!");
            }
        };
    }

}

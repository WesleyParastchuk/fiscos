package com.example.fiscos.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.fiscos.model.Classification;
import com.example.fiscos.model.ProcessedProduct;
import com.example.fiscos.model.ProductClassification;
import com.example.fiscos.model.RawProduct;
import com.example.fiscos.repository.ClassificationRepository;
import com.example.fiscos.repository.ProcessedProductRepository;

@Configuration
public class ProcessedProductSeeder {

    @Bean
    CommandLineRunner initProcessedProducts(ProcessedProductRepository repo, ClassificationRepository repoClassification) {
        return args -> {
            if (repo.count() == 0) {
                ProcessedProduct product1 = new ProcessedProduct("Produto A");
                ProcessedProduct product2 = new ProcessedProduct("Produto B");

                Classification classification1 = new Classification("Alimentos e Bebidas",
                        "Produtos alimentícios e bebidas");
                Classification classification2 = new Classification("Higiene e Limpeza",
                        "Produtos de higiene pessoal e limpeza doméstica");
                // Salvar primeiro as classificações
                repoClassification.save(classification1);
                repoClassification.save(classification2);

                // Depois criar ProductClassification com as classificações já persistidas
                product1.getProductClassifications().add(new ProductClassification(product1, classification1));
                product2.getProductClassifications().add(new ProductClassification(product2, classification2));

                RawProduct rawProduct1 = new RawProduct("Ingrediente A", "1234", product1);
                RawProduct rawProduct2 = new RawProduct("Ingrediente B", "5678", product2);
                product1.getRawProducts().add(rawProduct1);
                product2.getRawProducts().add(rawProduct2);
                repo.save(product1);
                repo.save(product2);

                System.out.println(">>> Classificações criados com sucesso!");
            }
        };
    }

}

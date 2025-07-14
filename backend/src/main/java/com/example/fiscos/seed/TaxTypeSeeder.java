package com.example.fiscos.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.fiscos.model.TaxType;
import com.example.fiscos.repository.TaxTypeRepository;

@Configuration
public class TaxTypeSeeder {
    @Bean
    CommandLineRunner initTaxSeeders(TaxTypeRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new TaxType("Imposto estadual", "ESTADUAL"));
                repo.save(new TaxType("Imposto federal", "FEDERAL"));
                System.out.println(">>> Tipos de tributos criados com sucesso!");
            }
        };
    }
}

package com.example.fiscos.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.fiscos.model.Classification;
import com.example.fiscos.repository.ClassificationRepository;

@Configuration
public class ClassificationSeeder {
    
    @Bean
    CommandLineRunner initDatabase(ClassificationRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                //repo.save(new Classification());
                System.out.println(">>> Classificações criados com sucesso!");
            }
        };
    }
}

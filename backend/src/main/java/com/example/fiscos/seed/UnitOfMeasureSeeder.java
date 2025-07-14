package com.example.fiscos.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.fiscos.model.UnitOfMeasure;
import com.example.fiscos.repository.UnitOfMeasureRepository;

@Configuration
public class UnitOfMeasureSeeder {

    @Bean
    CommandLineRunner initUnitsOfMeasure(UnitOfMeasureRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new UnitOfMeasure("Kilogram", "KG"));
                repo.save(new UnitOfMeasure("Unit", "UN"));
                System.out.println(">>> Unidades de medida criadas com sucesso!");
            }
        };
    }

}

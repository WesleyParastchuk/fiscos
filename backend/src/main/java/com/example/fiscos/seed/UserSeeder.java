package com.example.fiscos.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.fiscos.model.User;
import com.example.fiscos.repository.UserRepository;

@Configuration
public class UserSeeder {
   
    @Bean
    CommandLineRunner initDatabase(UserRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new User("Wesley Parastchuk"));
                System.out.println(">>> Usuários criados com sucesso!");
            }
        };
    }

}

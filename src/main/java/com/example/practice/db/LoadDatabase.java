package com.example.practice.db;

import com.example.practice.entities.Beneficiary;
import com.example.practice.repositories.BeneficiaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    @Bean
    CommandLineRunner initDatabase(BeneficiaryRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Beneficiary("John", "Smith")));
        };
    }
}

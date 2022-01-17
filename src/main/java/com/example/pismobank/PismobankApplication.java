package com.example.pismobank;

import com.example.pismobank.models.OperationType;
import com.example.pismobank.repositories.OperationTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class PismobankApplication {

    public static void main(String[] args) {
        SpringApplication.run(PismobankApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(OperationTypeRepository operationTypeRepository) {
        log.info("OperationType initial values");

        return args -> {
            operationTypeRepository.save(new OperationType(1L, "COMPRA A VISTA"));
            operationTypeRepository.save(new OperationType(2L, "COMPRA PARCELADA"));
            operationTypeRepository.save(new OperationType(3L, "SAQUE"));
            operationTypeRepository.save(new OperationType(4L, "PAGAMENTO"));
        };
    }


}

package com.miniTache.reservation_salles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ConfigurationPropertiesScan

public class ReservationSallesApplication {

    public static void main(String[] args) {

        SpringApplication.run(ReservationSallesApplication.class, args);

    }

}

package com.miniTache.reservation_salles.config;

import com.miniTache.reservation_salles.Enum.Role;
import com.miniTache.reservation_salles.Repository.UtilisateurRepository;
import com.miniTache.reservation_salles.modele.Utilisateur;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner creerAdmin(
            UtilisateurRepository utilisateurRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            String email = "admin@reservation.com";

            if (!utilisateurRepository.existsByEmail(email)) {
                Utilisateur admin = new Utilisateur();

                admin.setEmail(email);
                admin.setPassword(passwordEncoder.encode("Admin123!"));
                admin.setRole(Role.ROLE_ADMIN);

                utilisateurRepository.save(admin);
            }
        };
    }
}
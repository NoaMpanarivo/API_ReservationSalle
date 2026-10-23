package com.miniTache.reservation_salles.securite;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prive")
public class ProfilPriveController {

    @GetMapping("/profil")
    public String afficherProfil(Authentication authentication) {
        return "Bienvenue " + authentication.getName();
    }
}
package com.miniTache.reservation_salles.securite;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administration")
public class AdministrationController {

    @GetMapping("/statut")
    @PreAuthorize("hasRole('ADMIN')")
    public String statutAdministration() {
        return "Accès administrateur autorisé";
    }
}
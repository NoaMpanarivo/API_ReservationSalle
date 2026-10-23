package com.miniTache.reservation_salles.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

public record UtilisateurRequest(
        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "La forme de votre email est invalide")
        String email,

        @NotBlank(message = "Le mot de passe est obligatoire")
        @Size(min = 8, max = 64, message = "Le mot de passe doit etre entre 8 et 64 inclus")
        String password

) {
}

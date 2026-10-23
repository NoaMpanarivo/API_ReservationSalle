package com.miniTache.reservation_salles.response;

import com.miniTache.reservation_salles.Enum.Role;
import com.miniTache.reservation_salles.modele.Utilisateur;

public record UtilisateurResponse(
        Long idUser,
        String email,
        Role role
) {
    public static UtilisateurResponse from(Utilisateur user){
        return new UtilisateurResponse(
                user.getIdUser(),
                user.getEmail(),
                user.getRole()
        );
    }
}

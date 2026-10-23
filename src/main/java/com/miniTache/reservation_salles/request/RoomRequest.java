package com.miniTache.reservation_salles.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomRequest(

    @NotBlank(message = "Le nom de la salle est obligatoire")
    String name,

    @NotNull(message = "La capacite de la salle est obligatoire")
    Integer capacity,

    @NotNull(message = "Le floor est obligatoire")
    Integer floor
) {
}

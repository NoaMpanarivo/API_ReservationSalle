package com.miniTache.reservation_salles.response;


import com.miniTache.reservation_salles.Enum.Status;
import com.miniTache.reservation_salles.modele.Reservation;

import java.time.LocalDate;

public record ReservationResponse(
        Long idReservation,
        LocalDate startAt,
        LocalDate endAt,
        Status status
) {
    public static ReservationResponse from(Reservation reservation){
        return new ReservationResponse(
                reservation.getIdReservation(),
                reservation.getStartAt(),
                reservation.getEndAt(),
                reservation.getStatus()
        );
    }
}

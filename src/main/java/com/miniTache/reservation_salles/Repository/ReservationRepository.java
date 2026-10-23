package com.miniTache.reservation_salles.Repository;

import com.miniTache.reservation_salles.Enum.Status;
import com.miniTache.reservation_salles.modele.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser_Email(String email);

    boolean existsByRoom_IdRoom(Long idRoom);

    boolean existsByRoom_IdRoomAndStatusAndStartAtLessThanEqualAndEndAtGreaterThanEqual(
            Long roomId,
            Status status,
            LocalDate endAt,
            LocalDate startAt
    );
}
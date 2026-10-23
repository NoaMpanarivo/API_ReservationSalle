package com.miniTache.reservation_salles.service;

import com.miniTache.reservation_salles.Enum.Status;
import com.miniTache.reservation_salles.Repository.ReservationRepository;
import com.miniTache.reservation_salles.Repository.RoomRepository;
import com.miniTache.reservation_salles.Repository.UtilisateurRepository;
import com.miniTache.reservation_salles.modele.Reservation;
import com.miniTache.reservation_salles.modele.Room;
import com.miniTache.reservation_salles.modele.Utilisateur;
import com.miniTache.reservation_salles.request.ReservationRequest;
import com.miniTache.reservation_salles.response.ReservationResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            RoomRepository roomRepository,
            UtilisateurRepository utilisateurRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<ReservationResponse> listerReservations(
            Authentication authentication
    ) {
        List<Reservation> reservations = estAdmin(authentication)
                ? reservationRepository.findAll()
                : reservationRepository.findByUser_Email(authentication.getName());

        return reservations.stream()
                .map(ReservationResponse::from)
                .toList();
    }

    public ReservationResponse creerReservation(
            Long roomId,
            ReservationRequest request,
            Authentication authentication
    ) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Salle introuvable"
                ));

        Utilisateur user = utilisateurRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Utilisateur introuvable"
                ));

        boolean chevauchement = reservationRepository
                .existsByRoom_IdRoomAndStatusAndStartAtLessThanEqualAndEndAtGreaterThanEqual(
                        roomId,
                        Status.ACTIVE,
                        request.endAt(),
                        request.startAt()
                );

        if (chevauchement) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "La salle est déjà réservée sur cette période"
            );
        }

        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setStartAt(request.startAt());
        reservation.setEndAt(request.endAt());
        reservation.setStatus(Status.ACTIVE);

        return ReservationResponse.from(
                reservationRepository.save(reservation)
        );
    }

    public ReservationResponse annulerReservation(
            Long id,
            Authentication authentication
    ) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Réservation introuvable"
                ));

        boolean proprietaire = reservation.getUser()
                .getEmail()
                .equals(authentication.getName());

        if (!estAdmin(authentication) && !proprietaire) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Vous ne pouvez pas annuler cette réservation"
            );
        }

        if (reservation.getStatus() == Status.CANCELLED) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cette réservation est déjà annulée"
            );
        }

        reservation.setStatus(Status.CANCELLED);

        return ReservationResponse.from(
                reservationRepository.save(reservation)
        );
    }

    private boolean estAdmin(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority.getAuthority().equals("ROLE_ADMIN")
                );
    }
}
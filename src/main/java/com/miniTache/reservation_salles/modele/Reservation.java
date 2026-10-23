package com.miniTache.reservation_salles.modele;

import com.miniTache.reservation_salles.Enum.Status;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;

    @Column(name = "start_at", nullable = false)
    private LocalDate startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDate endAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Utilisateur user;

    public Reservation() {}

    public Long getIdReservation() { return idReservation; }
    public LocalDate getStartAt() { return startAt; }
    public void setStartAt(LocalDate startAt) { this.startAt = startAt; }
    public LocalDate getEndAt() { return endAt; }
    public void setEndAt(LocalDate endAt) { this.endAt = endAt; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public Utilisateur getUser() { return user; }
    public void setUser(Utilisateur user) { this.user = user; }
}
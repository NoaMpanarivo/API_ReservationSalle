package com.miniTache.reservation_salles.exception;


public class ReservationConflitException extends RuntimeException {

    public ReservationConflitException(String message){
        super(message);
    }
}
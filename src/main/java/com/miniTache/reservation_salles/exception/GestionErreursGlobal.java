package com.miniTache.reservation_salles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GestionErreursGlobal {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> gererValidation(MethodArgumentNotValidException exception){

        Map<String,String> erreurs = new LinkedHashMap<>();

        exception.getBindingResult().getFieldErrors().
                forEach(erreur -> erreurs.putIfAbsent(erreur.getField(),erreur.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erreurs);
    }

    @ExceptionHandler(DateException.class)
    public ResponseEntity<Map<String,String>> gererDate(DateException exception){
        Map<String,String> erreur = new LinkedHashMap<>();

        erreur.put("message",exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erreur);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,String>> gererJsonInvalide(HttpMessageNotReadableException exception){
        Map<String,String> erreur = new LinkedHashMap<>();
        erreur.put("message", "Corps de la requete invalide : verifiez le format des dates (yyyy-MM-dd) et les valeurs du status");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erreur);
    }

    @ExceptionHandler(ReservationConflitException.class)
    public ResponseEntity<Map<String,String>> gererConflit(ReservationConflitException exception){

        Map<String,String> erreur = new LinkedHashMap<>();

        erreur.put("message",exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erreur);             // 409
    }

}

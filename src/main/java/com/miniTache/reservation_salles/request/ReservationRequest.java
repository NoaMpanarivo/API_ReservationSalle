package com.miniTache.reservation_salles.request;




import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miniTache.reservation_salles.Enum.Status;
import com.miniTache.reservation_salles.exception.DateException;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationRequest(

      @NotNull(message = "La date de debbut est obligatoire")
      @Future
      @JsonFormat(pattern = "yyyy-MM-dd")
      LocalDate startAt,

      @NotNull(message = "La date de fin est obligatoire")
      @JsonFormat(pattern = "yyyy-MM-dd")
      @Future
      LocalDate endAt
)
{
      @AssertTrue(message = "La date de debut doit etre avant la date de fin")
      @JsonIgnore
      public boolean isPeriodeValide() {
            return startAt == null || endAt == null || startAt.isBefore(endAt);
      }
}
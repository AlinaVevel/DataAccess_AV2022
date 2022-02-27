package com.av2022.apirest.controllers;

import com.av2022.apirest.model.dao.IReservationEntityDAO;

import com.av2022.apirest.model.entity.ReservationJPAEntityFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest-av2022/reservation")
public class ReservationController {

    @Autowired
    private IReservationEntityDAO reservationEntityDAO;

    @GetMapping
    public List<ReservationJPAEntityFinal> findAllReservations() {
        return (List<ReservationJPAEntityFinal>) reservationEntityDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationJPAEntityFinal> findReservationById(@PathVariable(value = "id") int id) {
        Optional<ReservationJPAEntityFinal> Reservation = reservationEntityDAO.findById(id);

        if(Reservation.isPresent()) {
            return ResponseEntity.ok().body(Reservation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ReservationJPAEntityFinal saveReservation(@Validated @RequestBody ReservationJPAEntityFinal Reservation) {
        return reservationEntityDAO.save(Reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable(value = "id") int id) {
        Optional<ReservationJPAEntityFinal> Reservation = reservationEntityDAO.findById(id);
        if(Reservation.isPresent()) {
            reservationEntityDAO.deleteById(id);
            return ResponseEntity.ok().body("Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

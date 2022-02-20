package com.av2022.apirest.controllers;

import com.av2022.apirest.model.dao.IReservationEntityDAO;
import com.av2022.apirest.model.entity.ReservationJPAEntity;
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
    public List<ReservationJPAEntity> findAllReservations() {
        return (List<ReservationJPAEntity>) reservationEntityDAO.findAll();
    }

    @GetMapping("/{idreservation}")
    public ResponseEntity<ReservationJPAEntity> findReservationById(@PathVariable(value = "idreservation") int id) {
        Optional<ReservationJPAEntity> Reservation = reservationEntityDAO.findById(id);

        if(Reservation.isPresent()) {
            return ResponseEntity.ok().body(Reservation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ReservationJPAEntity saveReservation(@Validated @RequestBody ReservationJPAEntity Reservation) {
        return reservationEntityDAO.save(Reservation);
    }

    @DeleteMapping("/{idreservation}")
    public ResponseEntity<?> deleteReservation(@PathVariable(value = "idreservation") int id) {
        Optional<ReservationJPAEntity> Reservation = reservationEntityDAO.findById(id);
        if(Reservation.isPresent()) {
            reservationEntityDAO.deleteById(id);
            return ResponseEntity.ok().body("Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

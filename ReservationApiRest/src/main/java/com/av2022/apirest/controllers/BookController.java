package com.av2022.apirest.controllers;

import com.av2022.apirest.model.dao.IBookEntityDAO;
import com.av2022.apirest.model.dao.IReservationEntityDAO;
import com.av2022.apirest.model.entity.BooksJPAEntityFinal;
import com.av2022.apirest.model.entity.ReservationJPAEntityFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest-av2022/books")
public class BookController {

    @Autowired
    private IBookEntityDAO bookEntityDAO;

    @GetMapping
    public List<BooksJPAEntityFinal> findAllReservations() {
        return (List<BooksJPAEntityFinal>) bookEntityDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BooksJPAEntityFinal> findReservationById(@PathVariable(value = "id") String id) {
        Optional<BooksJPAEntityFinal> Books = bookEntityDAO.findById(id);

        if(Books.isPresent()) {
            return ResponseEntity.ok().body(Books.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public BooksJPAEntityFinal saveReservation(@Validated @RequestBody BooksJPAEntityFinal Book) {
        return bookEntityDAO.save(Book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable(value = "id") String id) {
        Optional<BooksJPAEntityFinal> Book = bookEntityDAO.findById(id);
        if(Book.isPresent()) {
            bookEntityDAO.deleteById(id);
            return ResponseEntity.ok().body("Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

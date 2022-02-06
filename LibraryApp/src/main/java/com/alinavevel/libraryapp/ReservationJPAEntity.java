package com.alinavevel.libraryapp;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "reservation", schema = "public", catalog = "Library")
public class ReservationJPAEntity {
    private int idreservation;
    private String isbn;
    private String code;
    private Date datereservation;


    @Id
    @Column(name = "idreservation", nullable = false)
    public int getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    @Basic
    @Column(name = "isbn", nullable = true, length = 13)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "code", nullable = true, length = 8)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "datereservation", nullable = true)
    public Date getDatereservation() {
        return datereservation;
    }

    public void setDatereservation(Date datereservation) {
        this.datereservation = datereservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationJPAEntity that = (ReservationJPAEntity) o;
        return idreservation == that.idreservation && Objects.equals(isbn, that.isbn) && Objects.equals(code, that.code) && Objects.equals(datereservation, that.datereservation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idreservation, isbn, code, datereservation);
    }
}

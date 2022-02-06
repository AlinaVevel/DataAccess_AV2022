package com.alinavevel.libraryapp;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "Library")
public class UsersJPAEntity {
    private String code;
    private String name;
    private String surname;
    private Date birthdate;
    private List<BooksJPAEntity> lentBook;
    private List<BooksJPAEntity> lentBookReservation;


    @Id
    @Column(name = "code", nullable = false, length = 8)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 25)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "birthdate", nullable = true)
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersJPAEntity that = (UsersJPAEntity) o;
        return Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(birthdate, that.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, surname, birthdate);
    }

    @ManyToMany(mappedBy = "borrower")
    public List<BooksJPAEntity> getLentBook() {
        return lentBook;
    }

    public void setLentBook(List<BooksJPAEntity> lentBook) {
        this.lentBook = lentBook;
    }

    @ManyToMany(mappedBy = "borrowerReservation")
    public List<BooksJPAEntity> getLentBookReservation() {
        return lentBookReservation;
    }

    public void setLentBookReservation(List<BooksJPAEntity> lentBookReservation) {
        this.lentBookReservation = lentBookReservation;
    }
}

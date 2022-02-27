package com.alinavevel.libraryapp;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Users jpa entity.
 */
@Entity
@Table(name = "users", schema = "public", catalog = "Library")
public class UsersJPAEntity {
    private String code;
    private String name;
    private String surname;
    private Date birthdate;
    private Date fined;
    private List<BooksJPAEntity> lentBook;
    private List<BooksJPAEntity> lentBookReservation;


    /**
     * Gets code.
     *
     * @return the code
     */
    @Id
    @Column(name = "code", nullable = false, length = 8)
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @Basic
    @Column(name = "name", nullable = false, length = 25)
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    @Basic
    @Column(name = "surname", nullable = false, length = 25)
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets birthdate.
     *
     * @return the birthdate
     */
    @Basic
    @Column(name = "birthdate", nullable = true)
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * Sets birthdate.
     *
     * @param birthdate the birthdate
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Gets fined.
     *
     * @return the fined
     */
    @Basic
    @Column(name = "fined", nullable = true)
    public Date getFined() {
        return fined;
    }

    /**
     * Sets fined.
     *
     * @param fined the fined
     */
    public void setFined(Date fined) {
        this.fined = fined;
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

    /**
     * Gets lent book.
     *
     * @return the lent book
     */
    @ManyToMany(mappedBy = "borrower")
    public List<BooksJPAEntity> getLentBook() {
        return lentBook;
    }

    /**
     * Sets lent book.
     *
     * @param lentBook the lent book
     */
    public void setLentBook(List<BooksJPAEntity> lentBook) {
        this.lentBook = lentBook;
    }

    /**
     * Gets lent book reservation.
     *
     * @return the lent book reservation
     */
    @ManyToMany(mappedBy = "borrowerReservation")
    public List<BooksJPAEntity> getLentBookReservation() {
        return lentBookReservation;
    }

    /**
     * Sets lent book reservation.
     *
     * @param lentBookReservation the lent book reservation
     */
    public void setLentBookReservation(List<BooksJPAEntity> lentBookReservation) {
        this.lentBookReservation = lentBookReservation;
    }
}

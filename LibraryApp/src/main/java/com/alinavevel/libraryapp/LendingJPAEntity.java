package com.alinavevel.libraryapp;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * The type Lending jpa entity.
 */
@Entity
@Table(name = "lending", schema = "public", catalog = "Library")
public class LendingJPAEntity {
    private int id;
    private Date lendingdate;
    private Date returningdate;
    private String book;
    private String borrower;


    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets lendingdate.
     *
     * @return the lendingdate
     */
    @Basic
    @Column(name = "lendingdate", nullable = false)
    public Date getLendingdate() {
        return lendingdate;
    }

    /**
     * Sets lendingdate.
     *
     * @param lendingdate the lendingdate
     */
    public void setLendingdate(Date lendingdate) {
        this.lendingdate = lendingdate;
    }

    /**
     * Gets returningdate.
     *
     * @return the returningdate
     */
    @Basic
    @Column(name = "returningdate", nullable = true)
    public Date getReturningdate() {
        return returningdate;
    }

    /**
     * Sets returningdate.
     *
     * @param returningdate the returningdate
     */
    public void setReturningdate(Date returningdate) {
        this.returningdate = returningdate;
    }

    /**
     * Gets book.
     *
     * @return the book
     */
    @Basic
    @Column(name = "book", nullable = false, length = 13)
    public String getBook() {
        return book;
    }

    /**
     * Sets book.
     *
     * @param book the book
     */
    public void setBook(String book) {
        this.book = book;
    }

    /**
     * Gets borrower.
     *
     * @return the borrower
     */
    @Basic
    @Column(name = "borrower", nullable = false, length = 13)
    public String getBorrower() {
        return borrower;
    }

    /**
     * Sets borrower.
     *
     * @param borrower the borrower
     */
    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LendingJPAEntity that = (LendingJPAEntity) o;
        return id == that.id && Objects.equals(lendingdate, that.lendingdate) && Objects.equals(returningdate, that.returningdate) && Objects.equals(book, that.book) && Objects.equals(borrower, that.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lendingdate, returningdate, book, borrower);
    }
}

package com.alinavevel.libraryapp;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "lending", schema = "public", catalog = "Library")
public class LendingJPAEntity {
    private int id;
    private Date lendingdate;
    private Date returningdate;
    private String book;
    private String borrower;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lendingdate", nullable = false)
    public Date getLendingdate() {
        return lendingdate;
    }

    public void setLendingdate(Date lendingdate) {
        this.lendingdate = lendingdate;
    }

    @Basic
    @Column(name = "returningdate", nullable = true)
    public Date getReturningdate() {
        return returningdate;
    }

    public void setReturningdate(Date returningdate) {
        this.returningdate = returningdate;
    }

    @Basic
    @Column(name = "book", nullable = false, length = 13)
    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    @Basic
    @Column(name = "borrower", nullable = false, length = 13)
    public String getBorrower() {
        return borrower;
    }

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

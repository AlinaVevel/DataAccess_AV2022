package com.alinavevel.libraryapp;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * The type Books jpa entity.
 */
@Entity
@Table(name = "books", schema = "public", catalog = "Library")
public class BooksJPAEntity {
    private String isbn;
    private String title;
    private Integer copies;
    private String cover;
    private String outline;
    private String publisher;
    private List<UsersJPAEntity> borrower;
    private List<UsersJPAEntity> borrowerReservation;


    /**
     * Gets isbn.
     *
     * @return the isbn
     */
    @Id
    @Column(name = "isbn", nullable = false, length = 13)
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets isbn.
     *
     * @param isbn the isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    @Basic
    @Column(name = "title", nullable = false, length = 90)
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets copies.
     *
     * @return the copies
     */
    @Basic
    @Column(name = "copies", nullable = true)
    public Integer getCopies() {
        return copies;
    }

    /**
     * Sets copies.
     *
     * @param copies the copies
     */
    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    /**
     * Gets cover.
     *
     * @return the cover
     */
    @Basic
    @Column(name = "cover", nullable = true, length = 255)
    public String getCover() {
        return cover;
    }

    /**
     * Sets cover.
     *
     * @param cover the cover
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * Gets outline.
     *
     * @return the outline
     */
    @Basic
    @Column(name = "outline", nullable = true, length = 255)
    public String getOutline() {
        return outline;
    }

    /**
     * Sets outline.
     *
     * @param outline the outline
     */
    public void setOutline(String outline) {
        this.outline = outline;
    }

    /**
     * Gets publisher.
     *
     * @return the publisher
     */
    @Basic
    @Column(name = "publisher", nullable = true, length = 60)
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets publisher.
     *
     * @param publisher the publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksJPAEntity that = (BooksJPAEntity) o;
        return Objects.equals(isbn, that.isbn) && Objects.equals(title, that.title) && Objects.equals(copies, that.copies) && Objects.equals(cover, that.cover) && Objects.equals(outline, that.outline) && Objects.equals(publisher, that.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, copies, cover, outline, publisher);
    }

    /**
     * Gets borrower.
     *
     * @return the borrower
     */
    @ManyToMany
    @JoinTable(name = "lending", catalog = "Library", schema = "public", joinColumns = @JoinColumn(name = "book", referencedColumnName = "isbn", nullable = false), inverseJoinColumns = @JoinColumn(name = "borrower", referencedColumnName = "code", nullable = false))
    public List<UsersJPAEntity> getBorrower() {
        return borrower;
    }

    /**
     * Sets borrower.
     *
     * @param borrower the borrower
     */
    public void setBorrower(List<UsersJPAEntity> borrower) {
        this.borrower = borrower;
    }

    /**
     * Gets borrower reservation.
     *
     * @return the borrower reservation
     */
    @ManyToMany
    @JoinTable(name = "reservation", catalog = "Library", schema = "public", joinColumns = @JoinColumn(name = "isbn", referencedColumnName = "isbn"), inverseJoinColumns = @JoinColumn(name = "code", referencedColumnName = "code"))
    public List<UsersJPAEntity> getBorrowerReservation() {
        return borrowerReservation;
    }

    /**
     * Sets borrower reservation.
     *
     * @param borrowerReservation the borrower reservation
     */
    public void setBorrowerReservation(List<UsersJPAEntity> borrowerReservation) {
        this.borrowerReservation = borrowerReservation;
    }
}

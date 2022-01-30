package com.alinavevel.libraryapp;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name="users", schema="public", catalog="Library")
public class Users {

    public Users(String code, String name, String surname, Date birthdate) {
        this.code = code;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "code", nullable = false, length = 8)
    private String code;
    @Basic
    @Column(name = "name", nullable = false, length = 25)
    private String name;
    @Basic
    @Column(name = "surname", nullable = false, length = 25)
    private String surname;
    @Basic
    @Column(name = "birthdate", nullable = true)
    private Date birthdate;

    public Users() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

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
        Users users = (Users) o;
        return Objects.equals(code, users.code) && Objects.equals(name, users.name) && Objects.equals(surname, users.surname) && Objects.equals(birthdate, users.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, surname, birthdate);
    }
}

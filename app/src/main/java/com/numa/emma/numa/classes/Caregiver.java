package com.numa.emma.numa.classes;

import java.io.Serializable;
import java.util.Objects;

public class Caregiver implements Serializable{
    private String name = "";
    private String surname = "";
    private String number = "";
    private String relationship = "";

    public Caregiver(){}

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public boolean paramSetted(){
        return  !(name.isEmpty() || surname.isEmpty() || relationship.isEmpty() || number.isEmpty());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Caregiver)) return false;
        Caregiver caregiver = (Caregiver) o;
        return Objects.equals(name, caregiver.name) &&
                Objects.equals(number, caregiver.number) &&
                Objects.equals(relationship, caregiver.relationship);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, number, relationship);
    }

    @Override
    public String toString() {
        return "Caregiver{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", relationship='" + relationship + '\'' +
                '}';
    }
}

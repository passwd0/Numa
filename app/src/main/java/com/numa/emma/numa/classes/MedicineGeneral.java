package com.numa.emma.numa.classes;

import com.numa.emma.numa.utils.MedicineTake;
import com.numa.emma.numa.utils.MedicineType;

import java.io.Serializable;
import java.util.Objects;

public class MedicineGeneral implements Serializable{
    private String name;
    private MedicineTake take;
    private MedicineType type;

    public MedicineGeneral(MedicineGeneral medicineGeneral){
        name = medicineGeneral.getName();
        take = medicineGeneral.getTake();
        type = medicineGeneral.getType();
    }

    public MedicineGeneral(String name, MedicineTake take, MedicineType type) {
        this.name = name;
        this.take = take;
        this.type = type;
    }

    public MedicineGeneral(){
        this("", MedicineTake.ORALE, MedicineType.PILLOLA);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MedicineTake getTake() {
        return take;
    }

    public void setTake(MedicineTake take) {
        this.take = take;
    }

    public MedicineType getType() {
        return type;
    }

    public void setType(MedicineType type) {
        this.type = type;
    }

    public boolean isPill(){
        return false;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicineGeneral)) return false;
        MedicineGeneral medicine = (MedicineGeneral) o;
        return Objects.equals(name, medicine.name) &&
                take == medicine.take &&
                type == medicine.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, take, type);
    }
}

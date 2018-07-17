package com.numa.emma.numa.classes;

import com.numa.emma.numa.utils.MedicineForm;
import com.numa.emma.numa.utils.MedicineTake;
import com.numa.emma.numa.utils.MedicineType;

import java.util.Objects;

public class MedicinePillola extends MedicineGeneral {
    private MedicineForm form;
    private int container;
    private int num;

    public MedicinePillola(MedicineGeneral medicine, MedicineForm form, int container, int num){
        super(medicine.getName(), medicine.getTake(), medicine.getType());
        this.form = form;
        this.container = container;
        this.num = num;
    }

    public MedicinePillola(MedicineGeneral medicine){
        this(medicine, MedicineForm.COMPRESSA_ROTONDA, 0, 0);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public MedicineTake getTake() {
        return super.getTake();
    }

    @Override
    public MedicineType getType() {
        return super.getType();
    }

    public MedicineForm getForm() {
        return form;
    }

    public void setForm(MedicineForm form) {
        this.form = form;
    }

    public int getContainer() {
        return container;
    }

    public void setContainer(int container) {
        this.container = container;
    }

    public int getNum() {
        return num;
    }

    public String getNumString(){
        return String.valueOf(num);
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "MedicinePillola{" +
                "form=" + form +
                ", container=" + container +
                ", num=" + num +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicinePillola)) return false;
        if (!super.equals(o)) return false;
        MedicinePillola that = (MedicinePillola) o;
        return container == that.container &&
                num == that.num &&
                form == that.form;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), form, container, num);
    }
}

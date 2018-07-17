package com.numa.emma.numa.classes;

import android.app.Application;
import android.util.Log;

import com.numa.emma.numa.utils.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Controller extends Application implements Serializable {
    private String name = "";
    private String surname = "";
    private String birth = "";
    private List<Caregiver> caregivers = new ArrayList<>();     //caregiver aggiunti in profile
    private List<MedicineGeneral> medicines = new ArrayList<>();       //medicine aggiunti in profile
    private List<Program> programs = new ArrayList<>();
    private Settings settings = new Settings();

    public void setName(String name){this.name = name;}

    public String getName(){return name;}

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Calendar getBirth() {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(Utils.dateFormatter.parse(birth));
        }catch (Exception e){
            Log.e("Controller", "birth: non e' una data");
        }
        return calendar;
    }

    public String getBirthString() { return birth; }

    public void setBirth(Calendar birth) {
        this.birth = Utils.dateFormatter.format(birth.getTime());
    }

    public void setBirth(String birth) {
        try {
                Utils.dateFormatter.parse(birth);
                this.birth = birth;
        }catch (Exception e){
            Log.w("Controller", "birth: data inserita non corretta");
        }
    }

    public List<Caregiver> getCaregiver() {
        return caregivers;
    }

    public void setCaregiver(List<Caregiver> caregiver) {
        this.caregivers = caregiver;
    }

    public void delCaregiver(Caregiver caregiver){
        this.caregivers.remove(caregiver);
    }

    public void addCaregiver(Caregiver caregiver){
        this.caregivers.add(caregiver);
    }

    public List<MedicineGeneral> getMedicine() {
        return medicines;
    }

    public MedicineGeneral getMedicine(int id){return medicines.get(id);}

    public List<MedicineGeneral> getMedicinesGeneral(){
        List<MedicineGeneral> medicines = new ArrayList<>();
        for (MedicineGeneral m : getMedicine()) {
            if (!(m instanceof MedicinePillola))
                medicines.add(m);
        }
        return medicines;
    }

    public List<MedicinePillola> getMedicinesPillola(){
        List<MedicinePillola> medicines = new ArrayList<>();
        for (MedicineGeneral m : getMedicine()) {
            if (m instanceof MedicinePillola)
                medicines.add((MedicinePillola) m);
        }
        return medicines;
    }

    public void setMedicine(List<MedicineGeneral> medicine) {
        this.medicines = medicine;
    }

    public void addMedicine(MedicineGeneral medicine){
        this.medicines.add(medicine);
    }

    public Program getProgram(int id) {return programs.get(id);}

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public void addProgram(Program program){
        this.programs.add(program);
    }

    public void delProgram(Program program){
        programs.remove(program);
    }

    public void delProgram(int id){
        programs.remove(id);
    }


    public void save(){
        try {
            FileOutputStream fileOut = new FileOutputStream("/storage/emulated/0/profile.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            Log.e("controller.save", "profile.dat: File non salvato");
        } catch (Exception e){
            e.getStackTrace();
        }
    }

    public boolean load(){
        try {
            FileInputStream fileIn = new FileInputStream("/storage/emulated/0/profile.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Controller controller = (Controller) in.readObject();
            in.close();
            fileIn.close();
            this.name = controller.getName();
            this.surname = controller.getSurname();
            this.birth = controller.getBirthString();
            this.caregivers = controller.getCaregiver();
            this.medicines = controller.getMedicine();
            this.programs = controller.getPrograms();
            this.settings = controller.getSettings();
            return true;
        } catch (IOException i) {
            Log.w("controller.load", "profile.dat: File non esistente");
            return false;
        } catch (ClassNotFoundException e){
            Log.e("controller.load", "profile.dat: File danneggiato");
            return false;
        } catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }

    public Settings getSettings() {
        return settings;
    }
}

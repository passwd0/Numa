/*
 * Created by passwd <passwd@mes3hacklab.org> on 2018
 * Copyright (c) 2018-2020. All rights reserved.
 * Last modified 7/24/18 4:11 PM
 */

package com.numa.emma.numa.classes;

import com.numa.emma.numa.utils.MedicineUnit;
import com.numa.emma.numa.utils.Utils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Program implements Serializable {
    private Calendar time;              //time
    private boolean everyDay;
    private List<Calendar> dates;       //date (solo se everyDay=false)
    private List<MedicineGeneral> medicines;
    private List<Integer> quantities;
    //optionals
    private boolean soundDispenser = false;
    private boolean vibDispenser = false;
    private boolean alarmWearable = false;
    private boolean soundWearable = false;
    private boolean vibWearable = false;

    public Program(Calendar time, List<Calendar> dates, boolean everyDay, List<MedicineGeneral> medicines, List<Integer> quantities) {
        this.time = time;
        this.dates = dates;
        this.medicines = medicines;
        this.quantities = quantities;
        this.everyDay = everyDay;
    }

    public Program(Calendar time, List<Calendar> dates, boolean everyDay){
        this(time, dates, everyDay, new ArrayList<MedicineGeneral>(), new ArrayList<Integer>());
    }

    public Program(){
        this(Calendar.getInstance(), new ArrayList<Calendar>(), true);
    }

    public List<Calendar> getDates() {
        return dates;
    }

    public void setTime(int hours, int minutes){
        time.set(Calendar.HOUR_OF_DAY,hours);
        time.set(Calendar.MINUTE,minutes);
        time.set(Calendar.SECOND,0);
        time.set(Calendar.MILLISECOND,0);
    }

    public Calendar getTime() {
        return time;
    }

    public String getTimeString(){
        return Utils.timeFormatter.format(time.getTime());
    }

    public void addDate(Calendar date) {
        dates.add(date);
    }

    public void setDates(List<Calendar> dates) {
        this.dates = dates;
    }

    public List<? extends MedicineGeneral> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineGeneral> medicines) {
        this.medicines = medicines;
    }

    public void addMedicine(MedicineGeneral medicine, int quantity) {
        int index = medicines.indexOf(medicine);
        if (index >= 0){
            if (quantity > 0)
                this.quantities.set(index, quantity);
            else {
                this.medicines.remove(index);
                this.quantities.remove(index);
            }
        } else{
            if (quantity > 0) {
                this.medicines.add(medicine);
                this.quantities.add(quantity);
            }
        }
    }

    public int getQuantity(MedicineGeneral medicine){
        int index = medicines.indexOf(medicine);
        if (index >= 0)
            return quantities.get(index);
        return 1;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public int getQuantity(int i){
        return quantities.get(i);
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }

    public boolean isEveryDay() {
        return everyDay;
    }

    public void setEveryDay(boolean everyDay) {
        this.everyDay = everyDay;
    }

    public boolean isSoundDispenser() {
        return soundDispenser;
    }

    public void setSoundDispenser(boolean soundDispenser) {
        this.soundDispenser = soundDispenser;
    }

    public boolean isVibDispenser() {
        return vibDispenser;
    }

    public void setVibDispenser(boolean vibDispenser) {
        this.vibDispenser = vibDispenser;
    }

    public boolean isAlarmWearable() {
        return alarmWearable;
    }

    public void setAlarmWearable(boolean alarmWearable) {
        this.alarmWearable = alarmWearable;
    }

    public boolean isSoundWearable() {
        return soundWearable;
    }

    public void setSoundWearable(boolean soundWearable) {
        this.soundWearable = soundWearable;
    }

    public boolean isVibWearable() {
        return vibWearable;
    }

    public void setVibWearable(boolean vibWearable) {
        this.vibWearable = vibWearable;
    }

    public String getUnit(int i){
        return MedicineUnit.values()[getMedicines().get(i).getType().ordinal()].toString();
    }

    public String toString(int i){
        return getMedicines().get(i).getName()+", "+getQuantity(i)+" "+getUnit(i);
    }

    @Override
    public String toString() {
        return "Program{" +
                "time=" + getTimeString() +
                ", dates=" + dates +
                ", medicines=" + medicines +
                ", quantities=" + quantities +
                ", everyDay=" + everyDay +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Program program = (Program) o;
        return everyDay == program.everyDay &&
                Objects.equals(time, program.time) &&
                Objects.equals(dates, program.dates) &&
                Objects.equals(medicines, program.medicines) &&
                Objects.equals(quantities, program.quantities);
    }

    @Override
    public int hashCode() {

        return Objects.hash(time, dates, medicines, quantities, everyDay);
    }
}

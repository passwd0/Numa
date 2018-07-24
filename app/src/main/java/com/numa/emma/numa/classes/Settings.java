/*
 * Created by passwd <passwd@mes3hacklab.org> on 2018
 * Copyright (c) 2018-2020. All rights reserved.
 * Last modified 7/24/18 4:11 PM
 */

package com.numa.emma.numa.classes;

import java.io.Serializable;

public class Settings implements Serializable {
    private int lightnessWearable;
    private int soundWearable;
    private boolean vibrationWearable;
    private int durationWearable;
    private int lightnessDispenser;
    private int soundDispenser;
    private int durationDispenser;
    private int notificationDispenser;

    public Settings() {
        lightnessWearable=0;
        soundWearable=0;
        vibrationWearable=false;
        durationWearable=0;
        lightnessDispenser=0;
        soundDispenser=0;
        durationDispenser=0;
        notificationDispenser=0;
    }

    public int getLightnessWearable() {
        return lightnessWearable;
    }

    public void setLightnessWearable(int lightnessWearable) {
        this.lightnessWearable = lightnessWearable;
    }

    public int getSoundWearable() {
        return soundWearable;
    }

    public void setSoundWearable(int soundWearable) {
        this.soundWearable = soundWearable;
    }

    public boolean isVibrationWearable() {
        return vibrationWearable;
    }

    public void setVibrationWearable(boolean vibrationWearable) {
        this.vibrationWearable = vibrationWearable;
    }

    public int getDurationWearable() {
        return durationWearable;
    }

    public void setDurationWearable(int durationWearable) {
        this.durationWearable = durationWearable;
    }

    public int getLightnessDispenser() {
        return lightnessDispenser;
    }

    public void setLightnessDispenser(int lightnessDispenser) {
        this.lightnessDispenser = lightnessDispenser;
    }

    public int getSoundDispenser() {
        return soundDispenser;
    }

    public void setSoundDispenser(int soundDispenser) {
        this.soundDispenser = soundDispenser;
    }

    public int getDurationDispenser() {
        return durationDispenser;
    }

    public void setDurationDispenser(int durationDispenser) {
        this.durationDispenser = durationDispenser;
    }

    public int getNotificationDispenser() {
        return notificationDispenser;
    }

    public void setNotificationDispenser(int notificationDispenser) {
        this.notificationDispenser = notificationDispenser;
    }
}
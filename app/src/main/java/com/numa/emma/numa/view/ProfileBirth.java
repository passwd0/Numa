/*
 * Created by passwd <passwd@mes3hacklab.org> on 2018
 * Copyright (c) 2018-2020. All rights reserved.
 * Last modified 7/24/18 4:11 PM
 */

package com.numa.emma.numa.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;

import java.util.Calendar;

public class ProfileBirth extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private Controller controller;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        controller = (Controller) getActivity().getApplicationContext();

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        controller.setBirth(c);

        EditText edit = getActivity().findViewById(R.id.birth_edit);
        edit.setText(controller.getBirthString());
    }
}
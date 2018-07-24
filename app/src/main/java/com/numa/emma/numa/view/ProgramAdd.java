/*
 * Created by passwd <passwd@mes3hacklab.org> on 2018
 * Copyright (c) 2018-2020. All rights reserved.
 * Last modified 7/24/18 4:11 PM
 */

package com.numa.emma.numa.view;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.numa.emma.numa.R;
import com.numa.emma.numa.adapters.ProgramAddName;
import com.numa.emma.numa.classes.Controller;
import com.numa.emma.numa.classes.Program;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProgramAdd extends Fragment {
    Controller controller;
    private Program program;
    private EditText hourS;
    private boolean toEdit = false;
    public static ProgramAddName listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.program_add, container, false);

        controller = (Controller) getActivity().getApplicationContext();

        ImageButton button_add = view.findViewById(R.id.add_button);
        hourS = view.findViewById(R.id.hour_edit);
        final RadioGroup radioGroup = view.findViewById(R.id.radioGroup_form);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        final RadioButton everyday = view.findViewById(R.id.everyday);
        final RadioButton personalize = view.findViewById(R.id.personalizate);
        final Button next = view.findViewById(R.id.next);

        try {
            program = (Program) getArguments().getSerializable("program");
            hourS.setText(program.getTimeString());
            if (program.isEveryDay())
                everyday.setChecked(true);
            else
                personalize.setChecked(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            listAdapter = new ProgramAddName(getActivity(), program);
            recyclerView.setAdapter(listAdapter);
        } catch (NullPointerException e) {
            Log.e("controller", "null pointer");
            program = new Program();
        }

        try {
            toEdit = getArguments().getBoolean("toEdit");
        } catch (NullPointerException e){
            Log.d("toEdit", "false");
        }catch (Exception e){
            e.getStackTrace();
        }

        hourS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timepicker();
            }
        });

        hourS.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                    timepicker();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                program.setEveryDay(i==R.id.everyday);
            }
        });

        personalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerBuilder builder = new DatePickerBuilder(getContext(), new OnSelectDateListener() {
                    @Override
                    public void onSelect(List<Calendar> calendar) {
                        program.setDates(calendar);
                    }
                }).pickerType(CalendarView.MANY_DAYS_PICKER);

                DatePicker datePicker = builder.build();
                datePicker.show();
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--------------------------temp
                List<Date> date = new ArrayList<>();
                //-------------------------------
                Bundle bundle = new Bundle();
                bundle.putSerializable("program", program);
                ProgramAddMedicine fragment = new ProgramAddMedicine();
                fragment.setArguments(bundle);
                fragment.show(getActivity().getSupportFragmentManager(), "ProgramAddMedicine");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!program.getMedicines().isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("program", program);
                    bundle.putBoolean("toEdit", toEdit);
                    ProgramAddOptions fragment = new ProgramAddOptions();
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
                }
            }
        });
        return view;
    }

    private void timepicker(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hourS.setText(String.format("%02d:%02d", selectedHour, selectedMinute));


                try {
                    program.setTime(selectedHour, selectedMinute);
                }catch (Exception e){
                    e.getStackTrace();
                }
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.show();
    }

    @Override
    public void onResume(){
        super.onResume();
        if (listAdapter != null)
            listAdapter.notifyDataSetChanged();
    }
}

package com.numa.emma.numa.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;

import java.util.ArrayList;
import java.util.List;

public class Calendar extends Fragment {
    private Controller controller;
    private OnSelectDateListener listener = new OnSelectDateListener() {
        @Override
        public void onSelect(List<java.util.Calendar> calendars) {
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ConstraintLayout view = (ConstraintLayout) inflater.inflate(R.layout.calendar, container, false);

        controller = (Controller) getActivity().getApplicationContext();

        CalendarView calendarView = view.findViewById(R.id.calendarView);

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        try {
            calendarView.setDate(calendar);
        }catch (Exception e){e.getStackTrace();}

        List<EventDay> events = new ArrayList<>();

        //------------------------------TEST------------------
        for (int i=1; i<20; i++){
            java.util.Calendar calendar1 = java.util.Calendar.getInstance();
            calendar1.add(java.util.Calendar.DATE, -i);
            events.add(new EventDay(calendar1, R.drawable.verde0));
        }
        //Calendar calendar1 = Calendar.getInstance();
        //events.add(new EventDay(calendar1, R.drawable.rosso0));
        //-------------------------------------------------------
        calendarView.setEvents(events);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Calendario");

        FragmentManager fm = getActivity().getSupportFragmentManager();
        for (int i=0; i<fm.getBackStackEntryCount(); i++)
            fm.popBackStack();
    }
}

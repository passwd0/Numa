package com.numa.emma.numa.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
 import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;
import com.numa.emma.numa.classes.Program;

public class ProgramAddMedicine extends DialogFragment {
    private Program program;
    private com.numa.emma.numa.adapters.ProgramAddMedicine listAdapterPillola;
    private com.numa.emma.numa.adapters.ProgramAddMedicine listAdapterGeneral;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.program_add_medicine, null);

        final Controller controller = (Controller) getActivity().getApplicationContext();

        try {
            program = (Program) getArguments().getSerializable("program");
        }catch (Exception e){e.getStackTrace();}

        final RecyclerView recyclerViewPillola = view.findViewById(R.id.medicine_pillola_list);
        recyclerViewPillola.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapterPillola = new com.numa.emma.numa.adapters.ProgramAddMedicine(getContext(), program, controller.getMedicinesPillola());
        recyclerViewPillola.setAdapter(listAdapterPillola);

        final RecyclerView recyclerViewGeneral = view.findViewById(R.id.medicine_general_list);
        recyclerViewGeneral.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapterGeneral = new com.numa.emma.numa.adapters.ProgramAddMedicine(getContext(), program, controller.getMedicinesGeneral());
        recyclerViewGeneral.setAdapter(listAdapterGeneral);

        builder.setView(view)
                .setPositiveButton("Fine", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        for (int i=0; i<controller.getMedicinesPillola().size(); i++){
                            if (listAdapterPillola.getToogle(i) && controller.getMedicinesPillola().get(i).equals(listAdapterPillola.getItem(i))) {
                                program.addMedicine(listAdapterPillola.getItem(i), listAdapterPillola.getQuantity(i));
                            }
                        }
                        for (int i=0; i<controller.getMedicinesGeneral().size(); i++){
                            if (listAdapterGeneral.getToogle(i) && controller.getMedicinesGeneral().get(i).equals(listAdapterGeneral.getItem(i))) {
                                program.addMedicine(listAdapterGeneral.getItem(i), listAdapterGeneral.getQuantity(i));
                            }
                        }
                        ProgramAdd.listAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancella", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ProgramAddMedicine.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
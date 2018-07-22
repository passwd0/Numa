package com.numa.emma.numa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.MedicineGeneral;
import com.numa.emma.numa.classes.Program;
import com.numa.emma.numa.utils.MedicineUnit;

import java.util.ArrayList;
import java.util.List;

public class ProgramAddMedicine extends RecyclerView.Adapter<ProgramAddMedicine.ViewHolder>{
    private Program program;
    private List<? extends MedicineGeneral> medicines;
    private Boolean[] toggles;
    private Integer[] quantities;
    private LayoutInflater mInflater;

    public ProgramAddMedicine(Context context, Program program, List<? extends MedicineGeneral> medicines) {
        this.mInflater = LayoutInflater.from(context);
        this.program = program;
        this.medicines = medicines;
        this.toggles = new Boolean[medicines.size()];
        this.quantities = new Integer[30];
        for (int i=0; i<medicines.size(); i++) {
            this.toggles[i] = false;
            this.quantities[i] = 0;
        }
    }

    // inflates the row layout from xml when needed
    @Override
    public ProgramAddMedicine.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.program_add_medicine_adapter, parent, false);
        return new ProgramAddMedicine.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ProgramAddMedicine.ViewHolder holder, final int position) {
        String name = medicines.get(position).getName();

        if (program != null){
            for (MedicineGeneral m : program.getMedicines()){
                if (getItem(position).equals(m)){
                    toggles[position] = true;
                    holder.radioButton.setChecked(true);
                    holder.spinner.setEnabled(true);
                    holder.spinner.setSelection(program.getQuantity(m));
                }
            }
        }
        holder.radioButton.setText(name);

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggles[position] = !toggles[position];
                holder.radioButton.setChecked(toggles[position]);
                holder.spinner.setEnabled(toggles[position]);
            }
        });

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quantities[position] = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        holder.unitTextView.setText(MedicineUnit.values()[medicines.get(position).getType().ordinal()].toString());
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;
        Spinner spinner;
        TextView unitTextView;

        ViewHolder(View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.radioButton);
            spinner = itemView.findViewById(R.id.spinner);
            unitTextView = itemView.findViewById(R.id.unit);

            addItemsOnSpinner();
        }

        private void addItemsOnSpinner() {
            List<String> quantitiesSpinner = new ArrayList<>();
            for (int i=0; i<=30; i++)
                quantitiesSpinner.add(String.valueOf(i));

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mInflater.getContext(), android.R.layout.simple_spinner_item, quantitiesSpinner);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
            spinner.setEnabled(false);
        }
    }

    // convenience method for getting data at click position
    public MedicineGeneral getItem(int id) {
        return medicines.get(id);
    }

    public boolean getToogle(int id){
        return toggles[id];
    }

    public int getQuantity(int id){
        return  quantities[id]; //quantities[id];
    }
}
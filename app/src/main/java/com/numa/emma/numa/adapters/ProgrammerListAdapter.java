package com.numa.emma.numa.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;
import com.numa.emma.numa.classes.MedicineGeneral;
import com.numa.emma.numa.classes.Program;
import com.numa.emma.numa.utils.MedicineType;
import com.numa.emma.numa.view.HomeProgram;
import com.numa.emma.numa.view.HomeProgramAdd;

public class ProgrammerListAdapter extends RecyclerView.Adapter<ProgrammerListAdapter.ViewHolder>{
    private Controller controller;
    private LayoutInflater mInflater;

    private ProgrammerListMedicineListAdapter programmerListMedicineListAdapter;

    public ProgrammerListAdapter(Context context, Controller controller) {
        this.controller = controller;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ProgrammerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.programmer_list_adapter, parent, false);
        return new ProgrammerListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProgrammerListAdapter.ViewHolder holder, final int position) {
        holder.hour.setText(controller.getProgram(position).getTimeString());

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mInflater.getContext()));
        programmerListMedicineListAdapter = new ProgrammerListMedicineListAdapter(mInflater.getContext(), controller.getProgram(position));
        holder.recyclerView.setAdapter(programmerListMedicineListAdapter);

        for (MedicineGeneral m : controller.getProgram(position).getMedicines())
            if (m.getType().equals(MedicineType.PILLOLA))
                holder.dispenser.setVisibility(View.VISIBLE);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("toEdit", true);
                bundle.putSerializable("program", controller.getProgram(position));
                HomeProgramAdd fragment = new HomeProgramAdd();
                fragment.setArguments(bundle);
                ((FragmentActivity) mInflater.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.delProgram(position);
                controller.save();
                HomeProgram.programmerListAdapter.notifyDataSetChanged();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hour;
        ImageView dispenser;
        RecyclerView recyclerView;
        ImageButton edit;
        ImageButton delete;

        ViewHolder(View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.hour);
            dispenser = itemView.findViewById(R.id.image_dispenser);
            recyclerView = itemView.findViewById(R.id.program_list);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    public Program getItem(int id) {
        return controller.getProgram(id);
    }

    @Override
    public int getItemCount() {
        return controller.getPrograms().size();
    }

}
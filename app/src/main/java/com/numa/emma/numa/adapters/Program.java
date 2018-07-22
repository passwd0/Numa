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
import android.widget.TextView;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;
import com.numa.emma.numa.view.ProgramAdd;

public class Program extends RecyclerView.Adapter<Program.ViewHolder>{
    private Controller controller;
    private LayoutInflater mInflater;

    private ProgramName programmerAddListAdapter;

    public Program(Context context, Controller controller) {
        this.controller = controller;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public Program.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.program_adapter, parent, false);
        return new Program.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Program.ViewHolder holder, final int position) {
        holder.hour.setText(controller.getProgram(position).getTimeString());

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mInflater.getContext()));
        programmerAddListAdapter = new ProgramName(mInflater.getContext(), controller.getProgram(position));
        holder.recyclerView.setAdapter(programmerAddListAdapter);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("toEdit", true);
                bundle.putSerializable("program", controller.getProgram(position));
                ProgramAdd fragment = new ProgramAdd();
                fragment.setArguments(bundle);
                ((FragmentActivity) mInflater.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.delProgram(position);
                controller.save();
                com.numa.emma.numa.view.Program.programmerListAdapter.notifyDataSetChanged();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hour;
        RecyclerView recyclerView;
        ImageButton edit;
        ImageButton delete;

        ViewHolder(View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.hour);
            recyclerView = itemView.findViewById(R.id.program_list);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    public com.numa.emma.numa.classes.Program getItem(int id) {
        return controller.getProgram(id);
    }

    @Override
    public int getItemCount() {
        return controller.getPrograms().size();
    }

}
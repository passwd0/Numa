package com.numa.emma.numa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.MedicinePillola;
import com.numa.emma.numa.classes.Program;

public class ProgrammerAddListAdapter extends RecyclerView.Adapter<ProgrammerAddListAdapter.ViewHolder> {
    private Program program;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public ProgrammerAddListAdapter(Context context, Program program) {
        this.mInflater = LayoutInflater.from(context);
        this.program = program;
    }

    // inflates the row layout from xml when needed
    @Override
    public ProgrammerAddListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.programmer_list_medicine_list_adapter, parent, false);
        return new ProgrammerAddListAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ProgrammerAddListAdapter.ViewHolder holder, final int position) {
        holder.iv_dispenser.setVisibility(program.getMedicines().get(position).isPill()?View.VISIBLE:View.INVISIBLE);
        holder.medicine.setText(program.getMedicines().get(position).getName());
        holder.unit.setText(program.getQuantities().get(position).toString());
    }

    @Override
    public int getItemCount() {
        return program.getMedicines().size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_dispenser;
        TextView medicine;
        TextView unit;

        ViewHolder(View itemView) {
            super(itemView);
            iv_dispenser = itemView.findViewById(R.id.image_dispenser);
            medicine = itemView.findViewById(R.id.tv_medicine_name);
            unit = itemView.findViewById(R.id.unit);
        }
    }
}
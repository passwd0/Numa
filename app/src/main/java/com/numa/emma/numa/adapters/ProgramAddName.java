package com.numa.emma.numa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Program;

public class ProgramAddName extends RecyclerView.Adapter<ProgramAddName.ViewHolder> {
    private Program program;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public ProgramAddName(Context context, Program program) {
        this.mInflater = LayoutInflater.from(context);
        this.program = program;
    }

    // inflates the row layout from xml when needed
    @Override
    public ProgramAddName.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.program_name_adapter, parent, false);
        return new ProgramAddName.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ProgramAddName.ViewHolder holder, final int position) {
        holder.iv_dispenser.setVisibility(View.GONE);
        holder.medicine.setText(program.toString(position));
    }

    @Override
    public int getItemCount() {
        return program.getMedicines().size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_dispenser;
        TextView medicine;

        ViewHolder(View itemView) {
            super(itemView);
            iv_dispenser = itemView.findViewById(R.id.iv_dispenser);
            medicine = itemView.findViewById(R.id.tv_name);
        }
    }
}
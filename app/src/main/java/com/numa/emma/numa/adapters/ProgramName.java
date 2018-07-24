/*
 * Created by passwd <passwd@mes3hacklab.org> on 2018
 * Copyright (c) 2018-2020. All rights reserved.
 * Last modified 7/24/18 4:11 PM
 */

package com.numa.emma.numa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.numa.emma.numa.R;

public class ProgramName extends RecyclerView.Adapter<ProgramName.ViewHolder> {
    private com.numa.emma.numa.classes.Program program;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public ProgramName(Context context, com.numa.emma.numa.classes.Program program) {
        this.mInflater = LayoutInflater.from(context);
        this.program = program;
    }

    // inflates the row layout from xml when needed
    @Override
    public ProgramName.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.program_name_adapter, parent, false);
        return new ProgramName.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ProgramName.ViewHolder holder, final int position) {
        holder.iv_dispenser.setVisibility(program.getMedicines().get(position).isPill()?View.VISIBLE:View.INVISIBLE);
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
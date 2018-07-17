package com.numa.emma.numa.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;
import com.numa.emma.numa.classes.MedicineGeneral;
import com.numa.emma.numa.view.ProfileMedicineGeneral;

import java.util.List;

public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListAdapter.ViewHolder> {
    private Controller controller;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public MedicineListAdapter(Context context, Controller controller) {
        this.mInflater = LayoutInflater.from(context);
        this.controller = controller;
    }

    @Override
    public MedicineListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.medicine_and_caregiver_list_adapter, parent, false);
        return new MedicineListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MedicineListAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(controller.getMedicine().get(position).getName());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("toEdit", true);
                bundle.putSerializable("medicine", controller.getMedicine().get(position));
                ProfileMedicineGeneral fragment = new ProfileMedicineGeneral();
                fragment.setArguments(bundle);
                ((FragmentActivity) mInflater.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return controller.getMedicine().size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
        }
    }

    // convenience method for getting data at click position
    public MedicineGeneral getItem(int id) {
        return controller.getMedicine().get(id);
    }
}
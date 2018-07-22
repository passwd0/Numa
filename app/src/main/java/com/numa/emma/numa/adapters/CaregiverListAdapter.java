package com.numa.emma.numa.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Caregiver;
import com.numa.emma.numa.classes.Controller;
import com.numa.emma.numa.view.ProfileCaregiver;

public class CaregiverListAdapter extends RecyclerView.Adapter<CaregiverListAdapter.ViewHolder>{
    private Controller controller;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public CaregiverListAdapter(Context context, Controller controller) {
        this.mInflater = LayoutInflater.from(context);
        this.controller = controller;
    }

    @Override
    public CaregiverListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.profile_list_adapter, parent, false);
        return new CaregiverListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CaregiverListAdapter.ViewHolder holder, final int position) {
        String name = controller.getCaregiver().get(position).getName();
        SpannableString content = new SpannableString(name);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        holder.textView.setText(content);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("toEdit", true);
                bundle.putSerializable("caregiver", controller.getCaregiver().get(position));
                ProfileCaregiver fragment = new ProfileCaregiver();
                fragment.setArguments(bundle);
                ((FragmentActivity) mInflater.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
        }
    }

    @Override
    public int getItemCount() {
        return controller.getCaregiver().size();
    }

    // convenience method for getting data at click position
    public Caregiver getItem(int id) {
        return controller.getCaregiver().get(id);
    }
}
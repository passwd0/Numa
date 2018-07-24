/*
 * Created by passwd <passwd@mes3hacklab.org> on 2018
 * Copyright (c) 2018-2020. All rights reserved.
 * Last modified 7/24/18 4:11 PM
 */

package com.numa.emma.numa.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.numa.emma.numa.R;
import com.numa.emma.numa.adapters.CaregiverListAdapter;
import com.numa.emma.numa.adapters.MedicineListAdapter;
import com.numa.emma.numa.classes.Caregiver;
import com.numa.emma.numa.classes.Controller;
import com.numa.emma.numa.classes.MedicineGeneral;

public class Profile extends Fragment {
    private Controller controller;
    private EditText editName;
    private EditText editSurname;
    private EditText editBirth;
    private MedicineListAdapter medicineAdapter;
    private CaregiverListAdapter caregiverAdapter;

    public Profile() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);

        controller = (Controller) getActivity().getApplicationContext();

        editName = view.findViewById(R.id.name_edit);
        editSurname = view.findViewById(R.id.surname_edit);
        editBirth = view.findViewById(R.id.birth_edit);
        final RecyclerView medicineList = view.findViewById(R.id.medicinali_list);
        final RecyclerView caregiverList = view.findViewById(R.id.caregiver_list);
        ImageButton buttonMedicine = view.findViewById(R.id.medicinali_button);

        editBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    DialogFragment dialogfragment = new ProfileBirth();
                    dialogfragment.show(getActivity().getSupportFragmentManager(), "datePickerDialog");
                }
            }
        });
        editBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogfragment = new ProfileBirth();
                dialogfragment.show(getActivity().getSupportFragmentManager(), "datePickerDialog");
            }
        });

        buttonMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("toEdit", false);
                bundle.putSerializable("medicine", new MedicineGeneral());
                ProfileMedicineGeneral fragment = new ProfileMedicineGeneral();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });

        ImageButton buttonCaregiver = view.findViewById(R.id.caregiver_button);
        buttonCaregiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("toEdit", false);
                bundle.putSerializable("caregiver", new Caregiver());
                ProfileCaregiver fragment = new ProfileCaregiver();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });

        medicineList.setLayoutManager(new LinearLayoutManager(getContext()));
        medicineAdapter = new MedicineListAdapter(getContext(), controller);
        medicineList.setAdapter(medicineAdapter);

        caregiverList.setLayoutManager(new LinearLayoutManager(getContext()));
        caregiverAdapter = new CaregiverListAdapter(getContext(), controller);
        caregiverList.setAdapter(caregiverAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Profilo");
        editName.setText(controller.getName());
        editSurname.setText(controller.getSurname());
        editBirth.setText(controller.getBirthString());
        medicineAdapter.notifyDataSetChanged();
        caregiverAdapter.notifyDataSetChanged();

        FragmentManager fm = getActivity().getSupportFragmentManager();
        for (int i=0; i<fm.getBackStackEntryCount(); i++)
            fm.popBackStack();
    }

    @Override
    public void onPause() {
        super.onPause();
        controller.setName(editName.getText().toString());
        controller.setSurname(editSurname.getText().toString());
        controller.save();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Setting()).addToBackStack(null).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
/*
 * Created by passwd <passwd@mes3hacklab.org> on 2018
 * Copyright (c) 2018-2020. All rights reserved.
 * Last modified 7/24/18 4:11 PM
 */

package com.numa.emma.numa.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Caregiver;
import com.numa.emma.numa.classes.Controller;

public class ProfileCaregiver extends Fragment {
    Controller controller;
    Caregiver caregiver;
    private boolean toEdit = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_caregiver, container, false);

        controller = (Controller) getActivity().getApplicationContext();

        final EditText nameEdit = view.findViewById(R.id.name_edit);
        final EditText surnameEdit = view.findViewById(R.id.surname_edit);
        final EditText relationshipEdit = view.findViewById(R.id.relationship_edit);
        final EditText numberEdit = view.findViewById(R.id.number_edit);
        final Button finishButton = view.findViewById(R.id.finish);


        try {
            caregiver = (Caregiver) getArguments().getSerializable("caregiver");
            toEdit = getArguments().getBoolean("toEdit");

            nameEdit.setText(caregiver.getName());
            surnameEdit.setText(caregiver.getSurname());
            relationshipEdit.setText(caregiver.getRelationship());
            numberEdit.setText(caregiver.getNumber());
        } catch (NullPointerException e){
            Log.e("ProfileCaregiver", "caregiver is null");
        } catch (Exception e){
            e.getStackTrace();
        }

        nameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                caregiver.setName(editable.toString());
            }
        });

        surnameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                caregiver.setSurname(editable.toString());
            }
        });

        numberEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                caregiver.setNumber(editable.toString());
            }
        });

        relationshipEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                caregiver.setRelationship(editable.toString());
            }
        });
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (caregiver.paramSetted()){
                    if (!toEdit)
                        controller.addCaregiver(caregiver);
                    getActivity().getSupportFragmentManager().popBackStack(null, getActivity().getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Profile()).commit();
                }
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        controller.save();
    }
}

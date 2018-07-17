package com.numa.emma.numa.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.numa.emma.numa.utils.MedicineTake;
import com.numa.emma.numa.utils.MedicineType;
import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;
import com.numa.emma.numa.classes.MedicineGeneral;

public class ProfileMedicineGeneral extends Fragment{
    private MedicineGeneral medicine;
    private boolean toEdit = false;

    public ProfileMedicineGeneral() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_medicine_general, container, false);

        final Controller controller = (Controller) getActivity().getApplicationContext();

        final EditText entry_name = view.findViewById(R.id.name_edit);
        final RadioGroup radioGroupTake = view.findViewById(R.id.radioGroup_take);
        final RadioGroup radioGroupType = view.findViewById(R.id.radioGroup_type);
        final EditText altroEdit = view.findViewById(R.id.altro_edit);
        final Button finish = view.findViewById(R.id.finish);

        try {
            medicine = (MedicineGeneral) getArguments().getSerializable("medicine");
            toEdit = getArguments().getBoolean("toEdit");

            entry_name.setText(medicine.getName());

            RadioButton r = (RadioButton) radioGroupTake.getChildAt(medicine.getTake().ordinal());
            r.setChecked(true);

            r = (RadioButton) radioGroupType.getChildAt(medicine.getType().ordinal());
            r.setChecked(true);
            if(medicine.getType().compareTo(MedicineType.PILLOLA) == 0)
                finish.setText(R.string.next);
            else
                finish.setText(R.string.finish);

            if (medicine.getType().equals(MedicineType.PILLOLA))
                finish.setText(R.string.next);
        } catch (Exception e){
            e.getStackTrace();
        }

        radioGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                MedicineType type = MedicineType.values()[getIndexChild(radioGroup)];
                medicine.setType(type);
                if(type.compareTo(MedicineType.PILLOLA) == 0)
                    finish.setText(R.string.next);
                else
                    finish.setText(R.string.finish);
            }
        });

        radioGroupTake.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                MedicineTake take = MedicineTake.values()[getIndexChild(radioGroup)];
                medicine.setTake(take);
                if (take.compareTo(MedicineTake.ALTRO) == 0)
                    altroEdit.setVisibility(View.VISIBLE);
                else
                    altroEdit.setVisibility(View.INVISIBLE);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!entry_name.getText().toString().isEmpty()) {
                    medicine.setName(entry_name.getText().toString());

                    if (medicine.getType().compareTo(MedicineType.PILLOLA) == 0) {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("toEdit", toEdit);
                        bundle.putSerializable("medicine", medicine);
                        ProfileMedicinePillola fragment = new ProfileMedicinePillola();
                        fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
                    } else {
                        if (!toEdit)
                            controller.addMedicine(medicine);
                        controller.save();
                        getActivity().getSupportFragmentManager().popBackStack(null, getActivity().getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Profile()).commit();
                    }
                }

            }
        });
        return view;
    }

    private int getIndexChild(RadioGroup radioGroup){
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        return radioGroup.indexOfChild(radioButton);
    }
}

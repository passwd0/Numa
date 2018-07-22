package com.numa.emma.numa.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;
import com.numa.emma.numa.classes.MedicineGeneral;
import com.numa.emma.numa.classes.MedicinePillola;
import com.numa.emma.numa.utils.MedicineForm;
import com.numa.emma.numa.utils.Utils;

public class ProfileMedicinePillola extends Fragment{
    Controller controller;
    private MedicinePillola medicine;
    private EditText numberEdit;
    private RadioGroup radioGroupForm;
    private RadioGroup radioGroupContainer;
    private boolean toEdit = false;

    public ProfileMedicinePillola() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_medicine_pillola, viewGroup, false);

        controller = (Controller) getActivity().getApplicationContext();
        radioGroupForm = view.findViewById(R.id.radioGroup_form);
        final TextView filter = view.findViewById(R.id.filter);
        radioGroupContainer = view.findViewById(R.id.radioGroup_container);
        numberEdit = view.findViewById(R.id.number_edit);
        final Button delete = view.findViewById(R.id.delete);
        final Button finish = view.findViewById(R.id.finish);

        //setting default params
        try {
            MedicineGeneral medicineGeneral = (MedicineGeneral) getArguments().getSerializable("medicine");
            toEdit = getArguments().getBoolean("toEdit");

            if (medicineGeneral instanceof MedicinePillola)
                medicine = (MedicinePillola) medicineGeneral;
            else
                medicine = new MedicinePillola(medicineGeneral);

            RadioButton form = (RadioButton) radioGroupForm.getChildAt(medicine.getForm().ordinal()*2);
            form.setChecked(true);

            RadioButton container = (RadioButton) radioGroupContainer.getChildAt(medicine.getContainer());
            container.setChecked(true);

            numberEdit.setText(medicine.getNumString());
        } catch (Exception e){
            e.getStackTrace();
        }

        //set invisibility container already used
        for (MedicinePillola m : controller.getMedicinesPillola()){
            if (!m.equals(medicine)) {
                RadioButton container = (RadioButton) radioGroupContainer.getChildAt(m.getContainer());
                container.setClickable(false);
                container.setVisibility(View.GONE);
            }
        }

        //set default container selected
        if (!toEdit){
            for (int i=0; i<8; i++){
                RadioButton container = (RadioButton) radioGroupContainer.getChildAt(i);
                if (container.isClickable()) {
                    container.setChecked(true);
                    break;
                }
            }
        }

        radioGroupForm.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int index = getIndexChildSelected(radioGroup)/2;
                medicine.setForm(MedicineForm.values()[index]);                                     //ci sono degli SPACE tra i child
                filter.setText(Utils.textfilter[index]);

            }
        });

        radioGroupContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                medicine.setContainer(getIndexChildSelected(radioGroup));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toEdit)
                    controller.delMedicine(medicine);
                getActivity().getSupportFragmentManager().popBackStack(null, getActivity().getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Profile()).commit();

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberS = numberEdit.getText().toString();

                try {
                    int number = 0;
                    if (!numberS.isEmpty())
                        number = Integer.parseInt(numberS);
                    if (number>=0){
                        medicine.setNum(number);
                        if (!toEdit)
                            controller.addMedicine(medicine);
                        controller.save();
                        getActivity().getSupportFragmentManager().popBackStack(null, getActivity().getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Profile()).commit();
                    }
                }catch (NumberFormatException e){
                    Log.i("parse", "error parsing 'number'");
                } catch (Exception e){
                    e.getStackTrace();
                }
            }
        });
        return view;
    }

    private int getIndexChildSelected(RadioGroup radioGroup){
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        return radioGroup.indexOfChild(radioButton);
    }
}

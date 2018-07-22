package com.numa.emma.numa.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;

public class ProgramAddOptions extends Fragment {
    private Controller controller;
    private com.numa.emma.numa.classes.Program program;
    private boolean toEdit = false;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.program_add_options, container, false);

        controller = (Controller) getActivity().getApplicationContext();

        final RadioButton soundDispenser = view.findViewById(R.id.sound_dispenser);
        final RadioButton soundWearable = view.findViewById(R.id.sound_wearable);
        final RadioButton vibDispenser = view.findViewById(R.id.vib_dispenser);
        final RadioButton vibWearable = view.findViewById(R.id.vib_wearable);
        final Switch alarmEnableWearable = view.findViewById(R.id.alarm_enable_wearable);
        final Button delButton = view.findViewById(R.id.del);
        final Button finishButton = view.findViewById(R.id.finish);

        try {
            program = (com.numa.emma.numa.classes.Program) getArguments().getSerializable("program");
            soundDispenser.setChecked(program.isSoundDispenser());
            soundWearable.setChecked(program.isSoundWearable());
            vibDispenser.setChecked(program.isVibDispenser());
            vibWearable.setChecked(program.isVibWearable());
            alarmEnableWearable.setChecked(program.isAlarmWearable());
        }catch (Exception e){e.getStackTrace();}

        try {
            toEdit = getArguments().getBoolean("toEdit");
        }catch (Exception e){
            e.getStackTrace();
        }

        soundDispenser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundDispenser.setChecked(!program.isSoundDispenser());
                program.setSoundDispenser(soundDispenser.isChecked());
            }
        });

        vibDispenser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibDispenser.setChecked(!program.isVibDispenser());
                program.setVibDispenser(vibDispenser.isChecked());
            }
        });

        soundWearable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundWearable.setChecked(!program.isSoundWearable());
                program.setSoundWearable(soundWearable.isChecked());
            }
        });

        vibWearable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibWearable.setChecked(!program.isVibWearable());
                program.setVibWearable(vibWearable.isChecked());
            }
        });

        alarmEnableWearable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                program.setAlarmWearable(b);
            }
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Program()).commit();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!toEdit)
                    controller.addProgram(program);
                controller.save();
                getActivity().getSupportFragmentManager().popBackStack(null, getActivity().getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Program()).commit();
            }
        });

        return view;
    }
}

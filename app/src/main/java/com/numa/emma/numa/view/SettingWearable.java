package com.numa.emma.numa.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;
import com.numa.emma.numa.classes.Settings;

public class SettingWearable extends Fragment {
    private Settings s;

    public SettingWearable(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_wearable, container, false);

        Controller controller = (Controller) getActivity().getApplicationContext();
        s = controller.getSettings();

        final SeekBar lightnessBar = view.findViewById(R.id.lightness_bar);
        final SeekBar soundBar = view.findViewById(R.id.sound_bar);
        final Switch vibration = view.findViewById(R.id.vibration_switch);
        final Spinner duration = view.findViewById(R.id.duration_spinner);
        final Button finish = view.findViewById(R.id.finish);

        lightnessBar.setProgress(controller.getSettings().getLightnessWearable());
        soundBar.setProgress(controller.getSettings().getSoundWearable());
        vibration.setChecked(controller.getSettings().isVibrationWearable());
        duration.setSelection(controller.getSettings().getDurationWearable());

        lightnessBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                s.setLightnessWearable(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        soundBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                s.setSoundWearable(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        vibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                s.setVibrationWearable(b);
            }
        });

        duration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s.setDurationWearable(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack(null, getActivity().getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Setting()).commit();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Numa Wearable");
    }
}

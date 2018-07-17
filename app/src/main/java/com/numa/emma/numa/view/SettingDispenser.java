package com.numa.emma.numa.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;
import com.numa.emma.numa.classes.MedicineGeneral;
import com.numa.emma.numa.classes.MedicinePillola;
import com.numa.emma.numa.classes.Settings;
import com.numa.emma.numa.utils.Utils;

public class SettingDispenser extends Fragment {
    private Settings s;

    public SettingDispenser(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_dispenser, container, false);

        Controller controller = (Controller) getActivity().getApplicationContext();
        s = controller.getSettings();

        final SeekBar lightnessBar = view.findViewById(R.id.lightness_bar);
        final SeekBar soundBar = view.findViewById(R.id.sound_bar);
        final Spinner duration = view.findViewById(R.id.duration_spinner);
        final Spinner notification = view.findViewById(R.id.notification_spinner);
        final int[] containers_iv = {R.id.iv_container1, R.id.iv_container2, R.id.iv_container3, R.id.iv_container4, R.id.iv_container5, R.id.iv_container6, R.id.iv_container7, R.id.iv_container8};
        final int[] containers_tv = {R.id.tv_medicine_name1, R.id.tv_medicine_name2, R.id.tv_medicine_name3, R.id.tv_medicine_name4, R.id.tv_medicine_name5, R.id.tv_medicine_name6, R.id.tv_medicine_name7, R.id.tv_medicine_name8};
        final ImageView[] containers_image = new ImageView[8];
        final TextView[] containers_name = new TextView[8];
        final Button finish = view.findViewById(R.id.finish);

        lightnessBar.setProgress(s.getLightnessDispenser());
        soundBar.setProgress(s.getSoundDispenser());
        duration.setSelection(s.getDurationDispenser());
        notification.setSelection(s.getNotificationDispenser());

        lightnessBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                s.setLightnessDispenser(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        soundBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                s.setSoundDispenser(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

            duration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s.setDurationDispenser(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        notification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s.setNotificationDispenser(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        for (int i=0; i<containers_tv.length; i++){
            for (MedicineGeneral m : controller.getMedicinesPillola()) {
                if (m instanceof MedicinePillola) {
                    if (((MedicinePillola) m).getContainer() == i) {
                        containers_image[i] = view.findViewById(containers_iv[i]);
                        containers_image[i].setImageResource(Utils.containers_iv_resource_black[i]);
                        containers_name[i] = view.findViewById(containers_tv[i]);
                        containers_name[i].setText(m.getName());
                    }
                }
            }
        }

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
        getActivity().setTitle("Numa Dispenser");
    }
}

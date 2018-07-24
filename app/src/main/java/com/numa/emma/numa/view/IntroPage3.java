/*
 * Created by passwd <passwd@mes3hacklab.org> on 2018
 * Copyright (c) 2018-2020. All rights reserved.
 * Last modified 7/24/18 4:11 PM
 */

package com.numa.emma.numa.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.numa.emma.numa.R;

public class IntroPage3 extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.intro_page3, container, false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Raleway-Medium.ttf");

        TextView tv_body = view.findViewById(R.id.tv_body);
        tv_body.setTypeface(typeface);

        return view;
    }
}
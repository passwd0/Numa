/*
 * Created by passwd <passwd@mes3hacklab.org> on 2018
 * Copyright (c) 2018-2020. All rights reserved.
 * Last modified 7/24/18 4:11 PM
 */

package com.numa.emma.numa.utils;

import com.numa.emma.numa.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Utils {
    public final static SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.ITALY);
    public final static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
    public final static int[] containers_iv_resource_black = {R.drawable.a22, R.drawable.a23, R.drawable.a24, R.drawable.a25, R.drawable.a26, R.drawable.a27, R.drawable.a28, R.drawable.a29};
    public final static int[] textfilter = {R.string.profile_addmedicine_filter1, R.string.profile_addmedicine_filter2, R.string.profile_addmedicine_filter3, R.string.profile_addmedicine_filter4};
    //public final static int[] intro_res = {R.drawable.introgiusta_01, R.drawable.introgiusta_02, R.drawable.introgiusta_03, R.drawable.introgiusta_04};
    public final static int[] intro_res = {R.layout.intro_page1, R.layout.intro_page2, R.layout.intro_page3, R.layout.intro_page4};
}

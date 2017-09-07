package com.recycler.velasquez.recyclercalc.Activities.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.recycler.velasquez.recyclercalc.R;

/**
 * Created by yadder on 8/6/17.
 */

public class BuyFragment extends Fragment {

    Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = (View) inflater.inflate(R.layout.fragment_buys, container, false);

        activity = this.getActivity();

        return v;
    }
}

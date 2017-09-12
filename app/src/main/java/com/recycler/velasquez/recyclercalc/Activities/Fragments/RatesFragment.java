package com.recycler.velasquez.recyclercalc.Activities.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.recycler.velasquez.recyclercalc.R;

import java.util.ArrayList;

/**
 * Created by yadder on 8/4/17.
 */

public class RatesFragment extends Fragment {

    FragmentActivity        context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = (View) inflater.inflate(R.layout.fragment_rates, container, false);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        context = (FragmentActivity) activity;
        super.onAttach(activity);
    }



}

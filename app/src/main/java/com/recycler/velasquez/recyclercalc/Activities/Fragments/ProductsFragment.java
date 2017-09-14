package com.recycler.velasquez.recyclercalc.Activities.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recycler.velasquez.recyclercalc.Activities.Dialogs.NewProductDialog;
import com.recycler.velasquez.recyclercalc.R;

/**
 * Created by yadder on 8/4/17.
 */

public class ProductsFragment extends Fragment {

    FragmentActivity        activity;

    RecyclerView            recyclerview_products;
    CoordinatorLayout       coordinator_content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = (View) inflater.inflate(R.layout.fragment_products, container, false);

        activity = this.getActivity();

        coordinator_content = (CoordinatorLayout) v.findViewById(R.id.coordinator_content);
        recyclerview_products = (RecyclerView) v.findViewById(R.id.recyclerview_products);

        recyclerview_products.setHasFixedSize(true);
        recyclerview_products.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        activity = (FragmentActivity) activity;
        super.onAttach(activity);
    }

}

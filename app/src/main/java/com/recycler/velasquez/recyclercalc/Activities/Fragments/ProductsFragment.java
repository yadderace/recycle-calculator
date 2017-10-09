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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.recycler.velasquez.recyclercalc.Activities.Dialogs.NewProductDialog;
import com.recycler.velasquez.recyclercalc.Adapters.RecyclerViewProductsAdapter;
import com.recycler.velasquez.recyclercalc.Models.Product;
import com.recycler.velasquez.recyclercalc.R;
import com.recycler.velasquez.recyclercalc.Utilities.RealmOperations;

import java.util.ArrayList;

/**
 * Created by yadder on 8/4/17.
 */

public class ProductsFragment extends Fragment {

    FragmentActivity        activity;

    RecyclerView            recyclerview_products;
    CoordinatorLayout       coordinator_content;
    LinearLayout            linearlayout_content;
    RelativeLayout          relativelayout_content;

    RecyclerViewProductsAdapter     adapter;
    ArrayList<Product>              products;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = (View) inflater.inflate(R.layout.fragment_products, container, false);

        activity = this.getActivity();

        linearlayout_content = (LinearLayout) v.findViewById(R.id.linearlayout_content);
        coordinator_content = (CoordinatorLayout) v.findViewById(R.id.coordinator_content);
        recyclerview_products = (RecyclerView) v.findViewById(R.id.recyclerview_products);
        relativelayout_content = (RelativeLayout) v.findViewById(R.id.relativelayout_content);

        products = new ArrayList<Product>();
        adapter = new RecyclerViewProductsAdapter(activity, products);

        recyclerview_products.setHasFixedSize(true);
        recyclerview_products.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerview_products.setAdapter(adapter);

        uploadRegisteredProducts();

        return v;
    }

    /**
     * Find all the records registered for products and update the adapter
     */
    public void uploadRegisteredProducts(){
        RealmOperations realmOperations = new RealmOperations();
        ArrayList<Product> products = realmOperations.getAllProducts();
        realmOperations.closeRealm();

        boolean showList = true;

        if(products != null) {
            this.products.clear();
            this.products.addAll(products);
        }
        else
            showList = false;

        if(showList && products.size() > 0) {
            relativelayout_content.setVisibility(View.GONE);
            linearlayout_content.setVisibility(View.VISIBLE);
        }
        else
        {
            linearlayout_content.setVisibility(View.GONE);
            relativelayout_content.setVisibility(View.VISIBLE);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Activity activity) {
        activity = (FragmentActivity) activity;
        super.onAttach(activity);
    }

}

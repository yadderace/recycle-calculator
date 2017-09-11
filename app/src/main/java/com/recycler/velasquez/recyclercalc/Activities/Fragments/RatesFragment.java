package com.recycler.velasquez.recyclercalc.Activities.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.recycler.velasquez.recyclercalc.Adapters.ProductRatesAdapter;
import com.recycler.velasquez.recyclercalc.Models.Configuration;
import com.recycler.velasquez.recyclercalc.Models.ConfigurationDetail;
import com.recycler.velasquez.recyclercalc.R;
import com.recycler.velasquez.recyclercalc.Utilities.RealmOperations;

import java.util.ArrayList;

/**
 * Created by yadder on 8/4/17.
 */

public class RatesFragment extends Fragment {

    FragmentActivity        context;

    RecyclerView            recyclerview_products_rates;
    FloatingActionButton    floatingbutton_save_configuration;
    CoordinatorLayout       coordinator_content;

    ArrayList<ConfigurationDetail>      configurationDetails;
    ArrayList<ConfigurationDetail>      configurationDetailsOriginal;
    ProductRatesAdapter                 adapter;
    RealmOperations                     realmOperations;
    Configuration                       currentConfiguration;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = (View) inflater.inflate(R.layout.fragment_rates, container, false);

        recyclerview_products_rates = (RecyclerView) v.findViewById(R.id.recyclerview_products_rates);
        floatingbutton_save_configuration = (FloatingActionButton) v.findViewById(R.id.floatingbutton_save_configuration);
        coordinator_content = (CoordinatorLayout) v.findViewById(R.id.coordinator_content);

        recyclerview_products_rates.setHasFixedSize(true);
        recyclerview_products_rates.setLayoutManager(new LinearLayoutManager(this.getContext()));
        configurationDetails = new ArrayList<ConfigurationDetail>();
        adapter = new ProductRatesAdapter(configurationDetails, this.getActivity());
        recyclerview_products_rates.setAdapter(adapter);

        findConfigurationDetails();

        floatingbutton_save_configuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "Se ha actualizado la nueva configuracion";
                if(checkIsDifferentConfiguration()){
                    //Different Configuration
                    realmOperations = new RealmOperations();
                    realmOperations.setActiveConfigurationsToFalse();
                    Configuration configuration = realmOperations.createNewConfiguration();
                    configuration.setActive(true);

                    realmOperations.saveConfiguration(configuration);
                }

                Snackbar mySnackbar = Snackbar.make(v.findViewById(R.id.coordinator_content), msg, Snackbar.LENGTH_LONG);
                mySnackbar.show();

                //closeFragment();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        context = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    private void findConfigurationDetails(){
        realmOperations = new RealmOperations();
        currentConfiguration = realmOperations.getActiveConfiguration();

        if(currentConfiguration == null) {
            //TODO Mostrar mensaje de error y finalizar la actividad
        }

        configurationDetails.clear();
        configurationDetails.addAll(realmOperations.getConfigurationDetailByConfiguration(currentConfiguration.getRecId()));
        realmOperations.closeRealm();

        adapter.notifyDataSetChanged();
    }

    private boolean checkIsDifferentConfiguration(){

        for(int idx = 0; idx < recyclerview_products_rates.getChildCount(); idx++){
            View v = recyclerview_products_rates.getChildAt(idx);
            int recId = Integer.parseInt(((TextView)v.findViewById(R.id.textview_product_code)).getText().toString());
            double price = Double.parseDouble(((EditText)v.findViewById(R.id.edittext_product_price)).getText().toString());
            ConfigurationDetail configurationProduct = findConfigurationProduct(recId);
            if(price != configurationProduct.getUnitValue())
                return true;
        }
        return false;
    }

    private ConfigurationDetail findConfigurationProduct(int recId){
        for(int idx = 0; idx < configurationDetails.size(); idx++){
            if(configurationDetails.get(idx).getProduct().getRecId() == recId)
                return configurationDetails.get(idx);
        }
        return  null;
    }

    private void saveNewConfiguration(){

    }

    private void closeFragment(){
        Fragment newFragment = new BuyFragment();
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();


        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.framelayout_content, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }


}

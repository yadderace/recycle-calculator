package com.recycler.velasquez.recyclercalc.Activities.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;

import com.recycler.velasquez.recyclercalc.Adapters.GridViewIconAdapter;
import com.recycler.velasquez.recyclercalc.Interfaces.OnCompleteSaveProductDialog;
import com.recycler.velasquez.recyclercalc.Models.Product;
import com.recycler.velasquez.recyclercalc.R;
import com.recycler.velasquez.recyclercalc.Utilities.Constants;
import com.recycler.velasquez.recyclercalc.Utilities.RealmOperations;

/**
 * Created by yadder on 9/13/17.
 */

public class NewRateDialog extends DialogFragment {

    private static final String TAG = "AKDialogFragment";

    private Context                 context;

    private EditText                edittext_new_rate_name,
                                    edittext_new_rate_description;
    private TextInputLayout         textinputlayout_rate_name,
                                    textinputlayout_rate_description;


    private InputMethodManager      inputMethodManager;


    private DialogFragment          dialogFragment;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_fragment_new_rate, container, false);

        dialogFragment = this;
        context = dialogFragment.getContext();
        inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_new_rate));

        edittext_new_rate_name = (EditText) rootView.findViewById(R.id.edittext_new_rate_name);
        edittext_new_rate_description = (EditText) rootView.findViewById(R.id.edittext_new_rate_description);
        textinputlayout_rate_name = (TextInputLayout) rootView.findViewById(R.id.textinputlayout_rate_name);
        textinputlayout_rate_description = (TextInputLayout) rootView.findViewById(R.id.textinputlayout_rate_description);
        

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }
        setHasOptionsMenu(true);


        edittext_new_rate_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textinputlayout_rate_name.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        edittext_new_rate_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textinputlayout_rate_description.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.menu_new_rate, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Save the product
        if (id == R.id.action_save) {
            if(checkData())
                saveRate();
            return true;

            //Close the dialog
        } else if (id == android.R.id.home) {
            dialogFragment.dismiss();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Check all the data for the new product
     * @return
     */
    private boolean checkData(){
        String productName = edittext_new_rate_name.getText().toString();
        String productDescription = edittext_new_rate_description.getText().toString();

        if(!isValidName(productName)){
            textinputlayout_rate_name.setError(getString(R.string.error_product_name_invalid));
            edittext_new_rate_name.requestFocus();
            inputMethodManager.showSoftInput(edittext_new_rate_name, InputMethodManager.SHOW_IMPLICIT);
            return  false;
        }

        if(!isValidDescription(productDescription)){
            textinputlayout_rate_description.setError(getString(R.string.error_product_description_invalid));
            edittext_new_rate_description.requestFocus();
            inputMethodManager.showSoftInput(edittext_new_rate_description, InputMethodManager.SHOW_IMPLICIT);
            return false;
        }

        return true;
    }


    /**
     * Check if the name of the product is valid.
     * @param name
     * @return
     */
    private boolean isValidName(String name){
        return name.length() > 0;
    }

    /**
     * Check if the description of the product is valid.
     * @param description
     * @return
     */
    private boolean isValidDescription(String description){
        return description.length() > 0;
    }

    public  void saveRate(){
        //TODO Get the data for the rate


        //TODO Build alert dialog to save the rate

        /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(getString(R.string.title_new_product));
        alertDialogBuilder
                .setMessage(getString(R.string.message_you_will_save_product) + Constants.SPACE + productName + Constants.QUESTION_MARK)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Product newProduct =  new Product();
                        newProduct.setName(productName);
                        newProduct.setDescription(edittext_new_rate_description.getText().toString());
                        newProduct.setIcon(gridViewIconAdapter.getIconSelectedId());

                        RealmOperations realmOperations = new RealmOperations();
                        realmOperations.saveProduct(newProduct);

                        onCompleteSaveProductDialog.onComplete(true);
                        dialogFragment.dismiss();

                    }
                })
                .setNegativeButton(android.R.string.no,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        final AlertDialog alertDialog = alertDialogBuilder.create();*/

        //TODO Show the dialog
        /*alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context,R.color.lightRed));
            }
        });
        alertDialog.show();*/
    }

}

package com.recycler.velasquez.recyclercalc.Adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.recycler.velasquez.recyclercalc.Models.ConfigurationDetail;
import com.recycler.velasquez.recyclercalc.Models.Product;
import com.recycler.velasquez.recyclercalc.R;

import java.util.ArrayList;

/**
 * Created by yadder on 7/31/17.
 */

public class ProductRatesAdapter extends RecyclerView.Adapter<ProductRatesAdapter.PRViewHolder> {

    private ArrayList<ConfigurationDetail> data;
    private Activity activity;
    private static final String DRAWABLE  = "drawable";


    public ProductRatesAdapter(ArrayList<ConfigurationDetail> data, Activity activity){
        this.data = data;
        this.activity = activity;
    }

    @Override
    public PRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate_product, parent, false);
        PRViewHolder pvh = new PRViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PRViewHolder holder, int position) {
        /*ConfigurationDetail configDetail = data.get(position);
        Product product = configDetail.getProduct();

        holder.textview_product_name.setText(product.getName());
        holder.textview_product_code.setText(String.valueOf(product.getRecId()));
        holder.edittext_product_price.setText(String.valueOf(configDetail.getUnitValue()));

        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier(product.getIcon(), DRAWABLE, activity.getPackageName());
        holder.imageview_product_icon.setImageDrawable(activity.getDrawable(resourceId));*/
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PRViewHolder extends RecyclerView.ViewHolder{

        TextView textview_product_name;
        TextView textview_product_code;
        EditText edittext_product_price;
        ImageView imageview_product_icon;

        public PRViewHolder(View itemView) {
            super(itemView);

            textview_product_name = (TextView)itemView.findViewById(R.id.textview_product_name);
            textview_product_code = (TextView)itemView.findViewById(R.id.textview_product_code);
            edittext_product_price = (EditText)itemView.findViewById(R.id.edittext_product_price);
            imageview_product_icon = (ImageView) itemView.findViewById(R.id.imageview_product_icon);
        }
    }
}

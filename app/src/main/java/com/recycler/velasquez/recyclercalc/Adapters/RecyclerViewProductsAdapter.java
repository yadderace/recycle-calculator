package com.recycler.velasquez.recyclercalc.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recycler.velasquez.recyclercalc.Models.Product;
import com.recycler.velasquez.recyclercalc.R;
import com.recycler.velasquez.recyclercalc.Utilities.Constants;
import com.recycler.velasquez.recyclercalc.ViewHolders.ProductViewHolder;

import java.util.ArrayList;

/**
 * Created by yadder on 10/9/17.
 */

public class RecyclerViewProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    ArrayList<Product>      products;
    Context                 context;

    public RecyclerViewProductsAdapter(Context context, ArrayList<Product> products){
        this.products = products;
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = products.get(position);

        holder.textview_product_name.setText(product.getName());
        holder.textview_product_description.setText(product.getDescription());
        holder.imageview_product_icon.setImageDrawable(context.getDrawable(
                context.getResources().getIdentifier(product.getIcon(), Constants.DRAWABLE_DIRECTORY, context.getPackageName())
        ));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}

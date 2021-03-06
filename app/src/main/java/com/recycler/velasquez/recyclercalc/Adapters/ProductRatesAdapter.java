package com.recycler.velasquez.recyclercalc.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.zagum.switchicon.SwitchIconView;
import com.recycler.velasquez.recyclercalc.Models.ConfigurationDetail;
import com.recycler.velasquez.recyclercalc.Models.Product;
import com.recycler.velasquez.recyclercalc.R;
import com.recycler.velasquez.recyclercalc.Utilities.Constants;

import java.util.ArrayList;

/**
 * Created by yadder on 7/31/17.
 */

public class ProductRatesAdapter extends RecyclerView.Adapter<ProductRatesAdapter.PRViewHolder> {

    private ArrayList<Product> data;
    private ArrayList<RateProduct>  rateProducts;
    private Context context;


    public ProductRatesAdapter(ArrayList<Product> data, Context context){
        this.data = data;
        this.context = context;
        this.rateProducts = new ArrayList<RateProduct>();
    }

    @Override
    public PRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate_product, parent, false);
        PRViewHolder pvh = new PRViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PRViewHolder holder, int position) {
        final Product product = data.get(position);;
        holder.textview_product_name.setText(product.getName());

        holder.imageview_product_icon.setImageDrawable(context.getDrawable(
                context.getResources().getIdentifier(product.getIcon(), Constants.DRAWABLE_DIRECTORY, context.getPackageName())
        ));
        holder.switchicon_product_enabled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.switchicon_product_enabled.switchState();

                if(holder.switchicon_product_enabled.isIconEnabled()) {

                    holder.edittext_product_price.setText("0.0");
                    holder.edittext_product_price.setEnabled(true);

                    RateProduct rateProduct = new RateProduct();
                    rateProduct.setProduct(product);
                    rateProduct.setProductRate(0);
                    rateProducts.add(rateProduct);

                }
                else
                {
                    holder.edittext_product_price.setText("");
                    holder.edittext_product_price.setEnabled(false);

                    for(int idx = 0; idx < rateProducts.size(); idx++){
                        if(rateProducts.get(idx).getProduct().getRecId() == product.getRecId())
                        {
                            rateProducts.remove(idx);
                            break;
                        }
                    }
                }
            }
        });
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
        EditText edittext_product_price;
        ImageView imageview_product_icon;
        SwitchIconView switchicon_product_enabled;

        public PRViewHolder(View itemView) {
            super(itemView);

            textview_product_name = (TextView)itemView.findViewById(R.id.textview_product_name);
            edittext_product_price = (EditText)itemView.findViewById(R.id.edittext_product_price);
            imageview_product_icon = (ImageView) itemView.findViewById(R.id.imageview_product_icon);
            switchicon_product_enabled = (SwitchIconView) itemView.findViewById(R.id.switchicon_product_enabled);
        }
    }

    public static class RateProduct{
        Product     product;
        double      productRate;

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public double getProductRate() {
            return productRate;
        }

        public void setProductRate(double productRate) {
            this.productRate = productRate;
        }
    }
}

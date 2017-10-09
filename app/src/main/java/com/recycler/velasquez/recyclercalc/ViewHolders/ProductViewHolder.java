package com.recycler.velasquez.recyclercalc.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.recycler.velasquez.recyclercalc.R;

/**
 * Created by yadder on 10/9/17.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

    public TextView    textview_product_name,
                textview_product_description;
    public ImageView   imageview_product_icon;

    public ProductViewHolder(View itemView) {
        super(itemView);
        textview_product_description = (TextView) itemView.findViewById(R.id.textview_product_description);
        textview_product_name = (TextView) itemView.findViewById(R.id.textview_product_name);
        imageview_product_icon = (ImageView) itemView.findViewById(R.id.imageview_product_icon);
    }
}

package com.recycler.velasquez.recyclercalc.ViewHolders;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.recycler.velasquez.recyclercalc.R;

/**
 * Created by yadder on 10/6/17.
 */

public class IconViewHolder {
    public ImageView imageview_product_icon;
    public ImageView imageview_product_selected_icon;
    public FrameLayout framelayout_product_icon;


    public IconViewHolder(View v){
        framelayout_product_icon = (FrameLayout) v.findViewById(R.id.framelayout_product_icon);
        imageview_product_icon = (ImageView) v.findViewById(R.id.imageview_product_icon);
        imageview_product_selected_icon = (ImageView) v.findViewById(R.id.imageview_product_selected_icon);
    }
}

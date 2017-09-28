package com.recycler.velasquez.recyclercalc.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.recycler.velasquez.recyclercalc.R;

/**
 * Created by yadder on 9/27/17.
 */

public class GridViewIconAdapter extends BaseAdapter {

    private Context context;
    private Integer[] icons;

    public GridViewIconAdapter(Context context){
        this.context = context;
        loadImagesIcons();
    }

    public GridViewIconAdapter(Context context, Integer[] icons){
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int i) {
        return icons[i];
    }

    @Override
    public long getItemId(int i) {
        return icons[i];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(icons[position]);
        return imageView;
    }

    /**
     * Fill the vector with the ids of the icon images.
     */
    private void loadImagesIcons(){
        icons = new Integer[]{
                R.drawable.ic_aluminum,
                R.drawable.ic_glass,
                R.drawable.ic_plastic,
                R.drawable.ic_hard_plastic,
                R.drawable.ic_cardboard,
                R.drawable.ic_paper,
                R.drawable.ic_milk_bottle,
                R.drawable.ic_milk,
                R.drawable.ic_bottle,

        };
    }
}

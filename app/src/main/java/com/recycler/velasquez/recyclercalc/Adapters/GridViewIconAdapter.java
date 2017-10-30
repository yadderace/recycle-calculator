package com.recycler.velasquez.recyclercalc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.recycler.velasquez.recyclercalc.R;
import com.recycler.velasquez.recyclercalc.Utilities.Constants;
import com.recycler.velasquez.recyclercalc.ViewHolders.IconViewHolder;

/**
 * Created by yadder on 9/27/17.
 */

public class GridViewIconAdapter extends BaseAdapter{

    private Context             context;
    private String[]            icons;
    private String              selected;
    private IconViewHolder      lastViewHolderSelected;

    private static final String    NONE = null;


    public GridViewIconAdapter(Context context){
        this.context = context;
        this.selected = NONE;
        loadImagesIcons();
    }

    public GridViewIconAdapter(Context context, String[] icons){
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
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        final IconViewHolder iconViewHolder;

        if (convertView == null) {
            convertView = (( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_product_icon, viewGroup, false);
            iconViewHolder = new IconViewHolder(convertView);

            convertView.setTag(iconViewHolder);

        } else {
            iconViewHolder = (IconViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < viewGroup.getChildCount(); i++)
                ((IconViewHolder) viewGroup.getChildAt(i).getTag()).imageview_product_selected_icon.setVisibility(View.GONE);

                //if(lastViewHolderSelected != null)
                //    lastViewHolderSelected.imageview_product_selected_icon.setVisibility(View.GONE);

                selected = icons[position];
                lastViewHolderSelected = (IconViewHolder)view.getTag();
                lastViewHolderSelected.imageview_product_selected_icon.setVisibility(View.VISIBLE);
            }
        });

        if(selected != NONE && icons[position] == selected) {
            lastViewHolderSelected = iconViewHolder;
            iconViewHolder.imageview_product_selected_icon.setVisibility(View.VISIBLE);
        }
        else
            iconViewHolder.imageview_product_selected_icon.setVisibility(View.GONE);


        iconViewHolder.imageview_product_icon.setImageDrawable(context.getDrawable(
                context.getResources().getIdentifier(icons[position], Constants.DRAWABLE_DIRECTORY, context.getPackageName())
        ));

        return convertView;
    }

    /**
     * Fill the vector with the ids of the icon images.
     */
    private void loadImagesIcons(){
        icons = new String[]{
                "ic_aluminum",
                "ic_glass",
                "ic_plastic",
                "ic_hard_plastic",
                "ic_cardboard",
                "ic_paper",
                "ic_milk_bottle",
                "ic_milk",
                "ic_bottle",

        };
    }

    /**
     * Return id of the selected icon
     * @return
     */
    public String getIconSelectedId(){
        return (selected != NONE)? selected: null;
    }
}

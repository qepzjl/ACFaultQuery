package com.example.acfaultquery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class BrandAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Brand> brands;
    
    public BrandAdapter(Context context, ArrayList<Brand> brands) {
        this.context = context;
        this.brands = brands;
    }
    
    @Override
    public int getCount() {
        return brands.size();
    }
    
    @Override
    public Object getItem(int position) {
        return brands.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_brand, parent, false);
        }
        
        ImageView logo = convertView.findViewById(R.id.brandLogo);
        TextView name = convertView.findViewById(R.id.brandName);
        
        Brand brand = brands.get(position);
        logo.setImageResource(brand.getLogoResId());
        name.setText(brand.getName());
        
        return convertView;
    }
}
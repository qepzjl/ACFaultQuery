package com.example.acfaultquery;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView brandGrid;
    private ArrayList<Brand> brands;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        brandGrid = findViewById(R.id.brandGrid);
        setupBrands();
        
        BrandAdapter adapter = new BrandAdapter(this, brands);
        brandGrid.setAdapter(adapter);
        
        brandGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, FaultCodeActivity.class);
                intent.putExtra("brand", brands.get(position).getName());
                startActivity(intent);
            }
        });
    }
    
    private void setupBrands() {
        brands = new ArrayList<>();
        brands.add(new Brand("格力", R.drawable.logo_gree));
        brands.add(new Brand("美的", R.drawable.logo_midea));
        brands.add(new Brand("海尔", R.drawable.logo_haier));
        brands.add(new Brand("奥克斯", R.drawable.logo_aux));
        brands.add(new Brand("大金", R.drawable.logo_daikin));
        brands.add(new Brand("松下", R.drawable.logo_panasonic));
    }
}
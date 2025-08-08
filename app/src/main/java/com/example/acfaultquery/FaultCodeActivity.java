package com.example.acfaultquery;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FaultCodeActivity extends AppCompatActivity {

    private TextView resultText;
    private EditText faultCodeInput;
    private String currentBrand;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_code);
        
        currentBrand = getIntent().getStringExtra("brand");
        setupUI();
        
        // 返回按钮事件
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        // 查询按钮事件
        Button queryButton = findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryFaultInfo();
            }
        });
    }
    
    private void setupUI() {
        TextView title = findViewById(R.id.titleText);
        title.setText(currentBrand + "空调故障查询");
        
        ImageView brandLogo = findViewById(R.id.brandLogo);
        faultCodeInput = findViewById(R.id.faultCodeInput);
        resultText = findViewById(R.id.resultText);
        
        // 根据品牌设置LOGO
        int logoResId = getLogoResId(currentBrand);
        if (logoResId != 0) {
            brandLogo.setImageResource(logoResId);
        }
    }
    
    private int getLogoResId(String brand) {
        switch (brand) {
            case "格力": return R.drawable.logo_gree;
            case "美的": return R.drawable.logo_midea;
            case "海尔": return R.drawable.logo_haier;
            case "奥克斯": return R.drawable.logo_aux;
            case "大金": return R.drawable.logo_daikin;
            case "松下": return R.drawable.logo_panasonic;
            default: return R.drawable.logo_default;
        }
    }
    
    private void queryFaultInfo() {
        String code = faultCodeInput.getText().toString().trim().toUpperCase();
        
        if (code.isEmpty()) {
            resultText.setText("请输入故障代码");
            return;
        }
        
        String faultInfo = readFaultInfo(currentBrand, code);
        resultText.setText(faultInfo);
    }
    
    private String readFaultInfo(String brand, String code) {
        try {
            InputStream is = getAssets().open("fault_codes.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length >= 5 && parts[0].equals(brand) && parts[1].equals(code)) {
                    return "故障：" + parts[2] + "\n原因：" + parts[3] + "\n解决方案：\n" + parts[4].replace("\\n", "\n");
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "未找到相关信息";
    }
}
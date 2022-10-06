package com.vnpt.mydailyfitness.HustCare;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.vnpt.mydailyfitness.R;

public class WaterActivity extends AppCompatActivity {
    private LinearLayout op50ml,op100ml,op150ml,op200ml,op250ml,opCustom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_activity_main);
    }
}

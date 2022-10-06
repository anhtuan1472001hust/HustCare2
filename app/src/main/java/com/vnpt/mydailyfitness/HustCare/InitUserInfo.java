package com.vnpt.mydailyfitness.HustCare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.vnpt.mydailyfitness.R;

public class InitUserInfo extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_user_info);
        button=findViewById(R.id.btnContinue);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InitUserInfo.this,WaterActivity.class);
                startActivity(intent);
            }
        });
    }
}

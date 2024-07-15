package com.example.tamagochi.cleaning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tamagochi.R;

public class CleaningActivity extends AppCompatActivity implements View.OnClickListener{
    private Bath bath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning);
        bath = findViewById(R.id.bath_panel);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_back_bath){
            if(bath.isWasCleaning())
                setResult(RESULT_OK);
            else
                setResult(RESULT_CANCELED);
            finish();
        }
    }
}
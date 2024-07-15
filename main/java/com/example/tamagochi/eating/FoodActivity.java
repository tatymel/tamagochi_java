package com.example.tamagochi.eating;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tamagochi.R;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener{

    private EatingPanel eatingPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        eatingPanel = findViewById(R.id.eating_panel);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_back_food){
            if(eatingPanel.isWasEating())
                setResult(RESULT_OK);
            else
                setResult(RESULT_CANCELED);
            finish();
        }
    }
}
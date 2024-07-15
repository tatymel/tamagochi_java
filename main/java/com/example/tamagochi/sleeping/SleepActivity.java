package com.example.tamagochi.sleeping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tamagochi.R;

public class SleepActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean switchIsOn;
    private boolean wasSleeping;
    private SleepFace sleepFace;
    private SwitchButton switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        switchIsOn = true;
        wasSleeping = false;
        sleepFace = findViewById(R.id.sleep_face);
        switchButton = findViewById(R.id.btn_switch);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_back){
            if(wasSleeping)
                setResult(RESULT_OK);
            else
                setResult(RESULT_CANCELED);
            finish();
        }else if(view.getId() == R.id.btn_switch){
            if(switchIsOn){
                wasSleeping = true;
                changeLight(false);
            }else{
                changeLight(true);
            }
            sleepFace.invalidate();
            switchButton.invalidate();
        }
    }

    private void changeLight(boolean toChange){
        switchIsOn = toChange;
        sleepFace.setWake(toChange);
        switchButton.setWake(toChange);
    }
}
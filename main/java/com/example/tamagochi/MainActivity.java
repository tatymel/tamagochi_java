package com.example.tamagochi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity{

    private GamePanel gamePanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gamePanel = findViewById(R.id.game_panel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult");

        if (requestCode == State.SAD.ordinal() && resultCode == RESULT_OK) {
            gamePanel.increaseState(State.SAD);
            gamePanel.fixed(State.SAD);
        }else if(requestCode == State.TIRED.ordinal()){
            if(resultCode == RESULT_OK)
                gamePanel.increaseState(State.TIRED);
            gamePanel.fixed(State.TIRED);
        }else if(requestCode == State.HUNGRY.ordinal()){
            if(resultCode == RESULT_OK)
                gamePanel.increaseState(State.HUNGRY);
            gamePanel.fixed(State.HUNGRY);
        }else if(requestCode == State.DIRTY.ordinal()){
            if(resultCode == RESULT_OK)
                gamePanel.increaseState(State.DIRTY);
            gamePanel.fixed(State.DIRTY);
        }
    }
}
package com.example.tamagochi.gameSnake;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tamagochi.HappinessStateButton;
import com.example.tamagochi.R;
import com.example.tamagochi.gameSnake.Game;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private Game game = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game = findViewById(R.id.game);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnBack){
            setResult(RESULT_OK);
            finish();
            game.setActive(false);
        }
        game.onClick(view);
    }
}
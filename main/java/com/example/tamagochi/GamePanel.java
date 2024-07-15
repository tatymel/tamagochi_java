package com.example.tamagochi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Printer;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GamePanel extends LinearLayout{

    private Face face;
    private HungerStateButton hungerStateButton;
    private FatigueStateButton fatigueStateButton;
    private HappinessStateButton happinessStateButton;
    private CleanlinessStateButton cleanlinessStateButton;

    private final long COUNT_DOWN_INTERVAL = 1000;
    private boolean gameOver = false;
    private long curTimer;
    private long bestTimer;
    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, COUNT_DOWN_INTERVAL) {
        @Override
        public void onTick(long l) {
            if(!isAnyoneFixing()) {
                reduceStates();
                checkStates();
                invalidate();
                if(gameOver) {
                    curTimer = l;
                    timer.cancel();
                }
            }
        }

        @Override
        public void onFinish() {
            invalidate();
        }
    };

    private boolean isAnyoneFixing(){
        return hungerStateButton.isFixing() || fatigueStateButton.isFixing() || happinessStateButton.isFixing() || cleanlinessStateButton.isFixing();
    }
    public void fixed(State fixedState){
        switch (fixedState){
            case HUNGRY:
                hungerStateButton.setFixing(false);
                break;
            case TIRED:
                fatigueStateButton.setFixing(false);
                break;
            case SAD:
                happinessStateButton.setFixing(false);
                break;
            case DIRTY:
                cleanlinessStateButton.setFixing(false);
                break;
        }
    }

    private boolean isAnyoneZero(){
        return hungerStateButton.greenPercent() == 0 ||
                fatigueStateButton.greenPercent() == 0 ||
                happinessStateButton.greenPercent() == 0 ||
                cleanlinessStateButton.greenPercent() == 0;
    }
    private void checkStates() {
        if(isAnyoneZero()){
            gameOver = true;
            hungerStateButton.setGameOver(true);
            fatigueStateButton.setGameOver(true);
            happinessStateButton.setGameOver(true);
            cleanlinessStateButton.setGameOver(true);
            face.setCurrentIndex(State.DEAD);
        }else if(hungerStateButton.greenPercent() < 50){
            face.setCurrentIndex(State.HUNGRY);
        }else if(fatigueStateButton.greenPercent() < 30){
            face.setCurrentIndex(State.TIRED);
        }else if(happinessStateButton.greenPercent() < 80){
            face.setCurrentIndex(State.SAD);
        }else if(cleanlinessStateButton.greenPercent() < 20){
            face.setCurrentIndex(State.DIRTY);
        }else{
            face.setCurrentIndex(State.USUAL);
        }
    }
    public void increaseState(State stateToIncrease){
        switch (stateToIncrease){
            case HUNGRY:
                hungerStateButton.increaseState();
                break;
            case TIRED:
                fatigueStateButton.increaseState();
                break;
            case SAD:
                happinessStateButton.increaseState();
                break;
            case DIRTY:
                cleanlinessStateButton.increaseState();
                break;

        }

    }

    public GamePanel(Context context) {
        super(context);
        init();
    }



    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        super.dispatchDraw(canvas);
        if(hungerStateButton != null) {

            hungerStateButton.invalidate();
            happinessStateButton.invalidate();
            cleanlinessStateButton.invalidate();
            fatigueStateButton.invalidate();
            face.invalidate();
        }
        if(gameOver){
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setAntiAlias(true);

            int canvasWidth = canvas.getWidth();
            String text = "Your time: " + curTimer;

            float textX = (canvasWidth - paint.measureText(text)) / 2;
            float textY = canvas.getHeight() / 2 - 280;

            canvas.drawText(text, textX, textY, paint);
        }
    }

    public GamePanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        bestTimer = 0;
        timer.start();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        hungerStateButton = findViewById(R.id.hunger_btn);
        fatigueStateButton = findViewById(R.id.fatigue_btn);
        happinessStateButton = findViewById(R.id.happiness_btn);
        cleanlinessStateButton = findViewById(R.id.cleanliness_btn);
        face = findViewById(R.id.face);
    }

    private void reduceStates(){
        if(hungerStateButton != null) {
            hungerStateButton.reduceState();
            happinessStateButton.reduceState();
            cleanlinessStateButton.reduceState();
            fatigueStateButton.reduceState();
        }
    }


}

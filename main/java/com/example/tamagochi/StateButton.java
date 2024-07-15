package com.example.tamagochi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public abstract class StateButton extends AppCompatButton implements View.OnClickListener{
    protected Bitmap avatar;
    protected StateBar stateBar;
    protected Context context;
    protected boolean isFixing;
    protected boolean gameOver;
    public StateButton(@NonNull Context context) {
        super(context);
        init(context);
    }

    public StateButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StateButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        this.stateBar = new StateBar();
        this.isFixing = false;
        this.gameOver = false;
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onClickAction();
    }

    protected abstract void onClickAction();
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isFixing() {
        return isFixing;
    }

    public void setFixing(boolean fixing) {
        isFixing = fixing;
    }
    public void increaseState() {
        stateBar.increaseGreenPercent();
    }

    public void reduceState() {
        stateBar.reduceGreenPercent();
    }
    public int greenPercent() {
        return stateBar.getGreenPercent();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect dst = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(avatar, null, dst, new Paint());
        stateBar.draw(canvas, dst);
    }
}

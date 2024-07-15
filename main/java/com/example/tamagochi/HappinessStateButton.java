package com.example.tamagochi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HappinessStateButton extends StateButton{

     public HappinessStateButton(@NonNull Context context) {
        super(context);
        init(context);
    }

    public HappinessStateButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HappinessStateButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.avatar = BitmapFactory.decodeResource(context.getResources(), R.drawable.snake);
        this.avatar = Bitmap.createScaledBitmap(this.avatar, 200, 200, true);
    }
     @Override
    public void onClickAction() {
        if(!gameOver) {
            Intent intent = new Intent(context, com.example.tamagochi.gameSnake.GameActivity.class);
            int requestCode = State.SAD.ordinal(); // Может быть любым уникальным числом
            isFixing = true;
            ((Activity) context).startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("happiness");
    }

}

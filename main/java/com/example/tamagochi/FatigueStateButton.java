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

public class FatigueStateButton extends StateButton{

    public FatigueStateButton(@NonNull Context context) {
        super(context);
        init(context);
    }

    public FatigueStateButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FatigueStateButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        this.avatar = BitmapFactory.decodeResource(context.getResources(), R.drawable.bed);
        this.avatar = Bitmap.createScaledBitmap(this.avatar, 540, 525, true);
    }
    @Override
    public void onClickAction() {
        if(!gameOver) {
            Intent intent = new Intent(context, com.example.tamagochi.sleeping.SleepActivity.class);
            int requestCode = State.TIRED.ordinal();
            isFixing = true;
            ((Activity) context).startActivityForResult(intent, requestCode);
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("in onDraw fatigue");
    }

}

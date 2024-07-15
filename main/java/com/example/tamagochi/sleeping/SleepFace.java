package com.example.tamagochi.sleeping;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tamagochi.R;

public class SleepFace extends View {
    private Bitmap wakeUpAvater;
    private Bitmap sleepAvatar;
    private boolean isWake;
    public SleepFace(Context context) {
        super(context);
        init(context);
    }

    public SleepFace(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SleepFace(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public SleepFace(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        wakeUpAvater = BitmapFactory.decodeResource(context.getResources(), R.drawable.wake_up_face);
        sleepAvatar = BitmapFactory.decodeResource(context.getResources(), R.drawable.sleeping_face);
        isWake = true;
    }

    public void setWake(boolean wake) {
        isWake = wake;
    }

    public boolean isWake() {
        return isWake;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Rect dst = new Rect(0, 0,canvas.getWidth(), canvas.getHeight());
        if(isWake){
            canvas.drawBitmap(wakeUpAvater, (Rect) null, dst, new Paint());
        }else{
            canvas.drawBitmap(sleepAvatar, null, dst, new Paint());
        }
    }
}

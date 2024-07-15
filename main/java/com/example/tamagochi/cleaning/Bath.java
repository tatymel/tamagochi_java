package com.example.tamagochi.cleaning;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tamagochi.R;

public class Bath extends View {

    private Bitmap openBath, closedBath, dirtyFace;
    private Context context;
    private boolean wasCleaning;
    private boolean isOpen;
    public Bath(Context context) {
        super(context);
        init(context);
    }

    public Bath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Bath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public Bath(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        this.wasCleaning = false;
        this.isOpen = true;
        this.openBath = BitmapFactory.decodeResource(context.getResources(), R.drawable.open_bath);
        this.closedBath = BitmapFactory.decodeResource(context.getResources(), R.drawable.closed_bath);
        this.dirtyFace = BitmapFactory.decodeResource(context.getResources(), R.drawable.dirty_face);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Rect dst = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
        Rect dstFace = new Rect(0, 0, canvas.getWidth() / 4, canvas.getHeight() / 5);

        if(isOpen){
            canvas.drawBitmap(openBath, null, dst, new Paint());
            canvas.drawBitmap(dirtyFace, null, dstFace, new Paint());
        }else{
            canvas.drawBitmap(closedBath, null, dst, new Paint());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(isOpen){
                wasCleaning = true;
                isOpen = false;
            }else{
                isOpen = true;
            }
            invalidate();
        }
        return true;
    }

    public boolean isWasCleaning() {
        return wasCleaning;
    }
}


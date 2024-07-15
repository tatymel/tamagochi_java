package com.example.tamagochi.sleeping;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.example.tamagochi.R;

public class SwitchButton extends AppCompatButton {

    private Bitmap avatar;
    private boolean isWake;
    public SwitchButton(@NonNull Context context) {
        super(context);
        init(context);
    }

    public SwitchButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwitchButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        avatar = BitmapFactory.decodeResource(context.getResources(), R.drawable.switch_on_off);
        avatar = Bitmap.createScaledBitmap(this.avatar, avatar.getWidth() / 50, avatar.getHeight() / 50, true);
        isWake = true;
        if(avatar == null)
            System.out.println("AVATAR == NULL");
    }

    public boolean isWake() {
        return isWake;
    }

    public void setWake(boolean wake) {
        isWake = wake;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect dst = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
        if(isWake){
            Rect src = new Rect(0, avatar.getHeight() / 4, avatar.getWidth(), avatar.getHeight() / 2);
            canvas.drawBitmap(avatar, src, dst, new Paint());
        }else{
           Rect src = new Rect(0, avatar.getHeight() / 2 + 10, avatar.getWidth(), 3 * avatar.getHeight() / 4 + 10);
            canvas.drawBitmap(avatar, src, dst, new Paint());
        }
    }
}

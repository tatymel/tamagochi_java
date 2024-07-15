package com.example.tamagochi.gameSnake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.tamagochi.R;

import java.util.Random;

public class GreenApple{
    private Bitmap image;
    private int x = 50, y = 100, h = 50;

    private int width, height;
    private int widthApple = 150, heightApple = 125;

    private boolean isShown = true;

    public GreenApple(Context context){
        super();
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.green_apple);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {

            windowManager.getDefaultDisplay().getMetrics(metrics);
            this.width = metrics.widthPixels;
            this.height = metrics.heightPixels;
        }

        Random random = new Random();
        x = random.nextInt(Math.max(width - 500, 50));
        y = random.nextInt(Math.max(height - 500, 50));
    }

    public void draw(Canvas canvas){
        Paint p = new Paint();
        Rect srcRec = new Rect(0, 0, image.getWidth(), image.getHeight());
        Rect dstRec = new Rect(x, y, x + widthApple, y + heightApple);
        if(isShown)
            canvas.drawBitmap(image, srcRec, dstRec, p);
        //canvas.drawRect(new Rect(x, y, x + h, y + h), p);
    }

    public Rect getRect(){
        return new Rect(x, y, x + widthApple, y + heightApple);
    }

    public void fastChangeLocation(){
        isShown = false;
        Random random = new Random();

        x = random.nextInt(Math.max(width - 500, 50));
        y = random.nextInt(Math.max(height - 500, 50));

        isShown = true;
    }
}

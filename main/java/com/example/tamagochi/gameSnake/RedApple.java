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

public class RedApple{
    private Bitmap image;
    private int x = 50, y = 100, h = 50;
    private int widthApple = 150, heightApple = 150;

    private int width, height;

    public RedApple(Context context){
        super();

        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_apple);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(metrics);
            this.width = metrics.widthPixels;
            this.height = metrics.heightPixels;
        }

        Random random = new Random();
        x = random.nextInt(width - 500);
        y = random.nextInt(height - 500);
    }

    public void draw(Canvas canvas){
        Paint p = new Paint();
        Rect srcRect = new Rect(0, 0, image.getWidth(), image.getHeight());
        Rect distRect = new Rect(x, y, x + widthApple, y + heightApple);
        canvas.drawBitmap(image, srcRect, distRect, p);
    }

    public Rect getRect(){
        return new Rect(x, y, x + widthApple, y + heightApple);
    }

    public void changeLocation(Rect head) {
        Random random = new Random();

        x = head.right + random.nextInt(500);
        y = head.bottom - random.nextInt(500);
    }
}

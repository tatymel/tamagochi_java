package com.example.tamagochi.eating;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tamagochi.R;

import java.util.ArrayList;
import java.util.Random;

public class EatingPanel extends View {
    private Bitmap avatar;
    private ArrayList<Rect> foods;
    private ArrayList<FoodDestination> destinations;
    private Context context;
    private int widthScreen, heightScreen, widthImage, heightImage;
    private final int AMOUNT_FOOD = 12;
    private boolean wasEating;
    public EatingPanel(Context context) {
        super(context);
        init(context);
    }

    public EatingPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EatingPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public EatingPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        this.wasEating = false;
        this.avatar = BitmapFactory.decodeResource(context.getResources(), R.drawable.morning_food2);
        foods = new ArrayList<>();
        destinations = new ArrayList<>();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if(foods.isEmpty()){
            setScreenImageSize(canvas);
            int dx = avatar.getWidth() / 4;
            int dy = avatar.getHeight() / 3;

            foods = new ArrayList<>();
            destinations = new ArrayList<>();
            for(int i = 0; i < 4; ++i){
                for(int j = 0; j < 3; ++j){
                    foods.add(new Rect(i * dx, j * dy, (i + 1) * dx, (j + 1) * dy));
                    generateDestination();
                }
            }
        }
        for(int i = 0; i < AMOUNT_FOOD; ++i){
            if(!destinations.get(i).isVisible())
                continue;
            Rect src = foods.get(i);
            int x = destinations.get(i).getX();
            int y = destinations.get(i).getY();
            Rect dst = new Rect(x, y, x + widthImage, y + heightImage);
            canvas.drawBitmap(avatar, src, dst, new Paint());
        }
    }

    private void setScreenImageSize(Canvas canvas){
        this.widthScreen = canvas.getWidth();
        this.heightScreen = canvas.getHeight();
        this.widthImage = widthScreen / 5;
        this.heightImage = heightScreen / 5;
    }

    private void generateDestination(){
        Random random = new Random();
        int x = random.nextInt(Math.max(widthScreen - 200, 50));
        int y = random.nextInt(Math.max(heightScreen - 200, 50));
        destinations.add(new FoodDestination(x, y));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            int xTouch = (int)event.getX();
            int yTouch = (int)event.getY();
            for(int i = 0; i < AMOUNT_FOOD; ++i){
                if(!destinations.get(i).isVisible())
                    continue;
                int x = destinations.get(i).getX();
                int y = destinations.get(i).getY();
                if(x <= xTouch && xTouch <= x + widthImage && y <= yTouch && yTouch <= y + heightImage){
                    destinations.get(i).setVisible(false);
                    wasEating = true;
                    invalidate();
                    return true;
                }
            }
        }
        return true;
    }

    public boolean isWasEating() {
        return wasEating;
    }
}

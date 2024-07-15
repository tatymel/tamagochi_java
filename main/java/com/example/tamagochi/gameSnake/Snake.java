package com.example.tamagochi.gameSnake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.LinkedList;

public class Snake extends LinkedList<Rect> {

    private boolean left = false, right = true, up = false, down = false;
    private int h = 100;
    private int width, height;
    private boolean isDead = false;
    public Snake(Context context) {
        for (int i = 0; i < 3; i++) {
            add(new Rect(h * (i + 1), 100, h * (i + 2), 200));
        }
       DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(metrics);
            this.width = metrics.widthPixels;
            this.height = metrics.heightPixels;
        }
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.argb(150, 180, 150, 100));
        for (Rect r : this) {
            canvas.drawRect(r, p);
        }
        this.width = canvas.getWidth();
        this.height = canvas.getHeight();

    }

    public void left() {
        left = true;
        right = false;
        up = false;
        down = false;
    }

    public void right() {
        left = false;
        right = true;
        up = false;
        down = false;
    }

    public void up() {
        left = false;
        right = false;
        up = true;
        down = false;
    }

    public void down() {
        left = false;
        right = false;
        up = false;
        down = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void move() {

        Rect r = removeLast();
        r.set(getFirst());

        if (left) {
            r.left -= h;
            r.right -= h;
        }
        if (right) {
            r.left += h;
            r.right += h;
        }
        if (up) {
            r.top -= h;
            r.bottom -= h;
        }
        if (down) {
            r.top += h;
            r.bottom += h;
        }
        addFirst(r);
        if(r.right > width || r.bottom > height
        || r.left < 0 || r.top < 0){
            isDead = true;
        }
    }


    public void addTail() {
        Rect r_last = new Rect();
        r_last.set(getLast());
        addLast(r_last);
    }

    public Rect getHead(){
        return getFirst();
    }
}

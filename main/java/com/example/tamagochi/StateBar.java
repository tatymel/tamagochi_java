package com.example.tamagochi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class StateBar {
    private int leftG, topG, rightG, bottomG;
    private int leftR, topR, rightR, bottomR;
    private int greenPercent;
    private int redPercent;

    private int step;

    public int getGreenPercent() {
        return greenPercent;
    }

    StateBar(){
        greenPercent = 100;
        redPercent = 0;
        step = 5;
    }

    private void initRects(Rect mainRect){
        int widthImage = mainRect.right - mainRect.left;
        int heightImage = mainRect.bottom - mainRect.top;

        leftG = mainRect.left;
        topG = mainRect.top + heightImage * 80 / 100;
        rightG = mainRect.left + widthImage * greenPercent / 100;
        bottomG = mainRect.top + heightImage;

        leftR = mainRect.left + widthImage - widthImage * redPercent / 100;
        topR = mainRect.top + heightImage * 80 / 100;
        rightR = mainRect.left + widthImage;
        bottomR = mainRect.top + heightImage;
    }


    public void draw(Canvas canvas, Rect mainRect){
        initRects(mainRect);
        Rect greenRect = new Rect(leftG, topG, rightG, bottomG);
        Rect redRect = new Rect(leftR, topR, rightR, bottomR);


        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.GREEN);
        rectPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(greenRect, rectPaint);

        rectPaint.setColor(Color.RED);
        rectPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(redRect, rectPaint);

        rectPaint.setColor(Color.BLACK);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(10);
        canvas.drawRect(greenRect, rectPaint);
        canvas.drawRect(redRect, rectPaint);

    }

    public void reduceGreenPercent() {
        this.greenPercent -= step;
        this.redPercent += step;
        System.out.println(greenPercent);
    }

    public void increaseGreenPercent(){
        this.greenPercent = 100;
        this.redPercent = 0;
    }


}

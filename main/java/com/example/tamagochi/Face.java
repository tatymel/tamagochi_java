package com.example.tamagochi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Face extends View{

    private Bitmap avatar;
    private ArrayList<Rect> expressions;
    private ArrayList<String> statements;
    private State currentState;
    public void setCurrentIndex(State state) {

        this.currentState = state;
    }

    public State getCurrentIndex() {
        return currentState;
    }

    public Face(Context context) {
        super(context);
        init(context);
    }

    public Face(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Face(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public Face(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void initExpressions(){
        int dx = avatar.getWidth() / 3;
        int dy = avatar.getHeight() / 5;

        expressions.add(new Rect(dx, dy, dx * 2, dy * 2));
        statements.add("I AM HAPPY!");

        expressions.add(new Rect(dx * 2, dy * 2, dx * 3, dy * 3));
        statements.add("I AM HUNGRY!");

        expressions.add(new Rect(dx * 2, dy * 3, dx * 3, dy * 4));
        statements.add("I AM TIRED!");

        expressions.add(new Rect(0, dy, dx, dy * 2));
        statements.add("I AM SAD!");

        expressions.add(new Rect(0, dy * 2, dx, dy * 3));
        statements.add("I AM DIRTY!");

        expressions.add(new Rect(dx, dy * 4, dx * 2, dy * 5));
        statements.add("I AM DEAD(((");
    }

    public void init(Context context){
        this.avatar = BitmapFactory.decodeResource(context.getResources(), R.drawable.face);
        this.avatar = Bitmap.createScaledBitmap(this.avatar, this.avatar.getWidth() / 10, this.avatar.getHeight() / 10, true);
        currentState = State.USUAL;
        expressions = new ArrayList<>();
        statements = new ArrayList<>();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        if(expressions.isEmpty())
            initExpressions();
        /*Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);
        avatar = Bitmap.createBitmap(avatar, 0, 0, avatar.getWidth(), avatar.getHeight(), matrix, true);
       */ Rect src = expressions.get(currentState.ordinal());
        Rect dst = new Rect(canvas.getWidth() / 2 - avatar.getWidth() / 2, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(avatar, src, dst, new Paint());
        drawText(canvas);
        System.out.println("in onDraw face");
    }

    private void drawText(@NonNull Canvas canvas){

        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setAntiAlias(true);

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        String text = statements.get(currentState.ordinal());

        float textX = (canvasWidth - paint.measureText(text)) / 2;
        float textY = canvasHeight - 30;

        canvas.drawText(text, textX, textY, paint);
    }

}

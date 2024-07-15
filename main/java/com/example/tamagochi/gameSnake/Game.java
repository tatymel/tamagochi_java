package com.example.tamagochi.gameSnake;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.tamagochi.R;

public class Game extends View {

    private GreenApple greenApple;
    private RedApple redApple;
    private Snake snake;
    private Context context;

    private boolean isActive = false;

    private boolean answerIsGot = false;
    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 250) {
        @Override
        public void onTick(long l) {
            if(!snake.isDead()) {
                answerIsGot = false;
                if(snakeAteGA()){
                    snake.addTail();
                }
                if(snakeAteRA()){
                    snake.setDead(true);
                }
                snake.move();
                invalidate(); //перерисовка
            }else{
                if(!answerIsGot && isActive) {
                    showGameOverDialog();
                    answerIsGot = true;
                }
            }
        }

        @Override
        public void onFinish() {
        }

    };

    public void setActive(boolean active) {
        isActive = active;
    }

    public Game(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        snake = new Snake(context);
        greenApple = new GreenApple(context);
        redApple = new RedApple(context);
        isActive = true;

        timer.start();
        this.context = context;
    }

    private boolean snakeAteGA(){
        Rect head = snake.getHead();
        Rect apple = greenApple.getRect();
        if(Rect.intersects(head, apple)){
            greenApple.fastChangeLocation();
            redApple.changeLocation(snake.getHead());
            return true;
        }
        return false;
    }

    private  boolean snakeAteRA(){
        Rect head = snake.getHead();
        Rect apple = redApple.getRect();
        if(Rect.intersects(head, apple)){
            return true;
        }
        return false;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!snake.isDead()) {
            snake.draw(canvas);
            greenApple.draw(canvas);
            redApple.draw(canvas);
        }
    }


    public void onClick(View view) {
        if (view.getId() == R.id.btnLeft) {
            snake.left();
        }
        if (view.getId() == R.id.btnRight) {
            snake.right();
        }
        if (view.getId() == R.id.btnUp) {
            snake.up();
        }
        if (view.getId() == R.id.btnDown) {
            snake.down();
        }
    }

    private void showGameOverDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Game over")
                .setCancelable(false)
                .setPositiveButton("Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        snake = new Snake(context);
                        answerIsGot = true;
                        greenApple.fastChangeLocation();
                        redApple.changeLocation(snake.getHead());
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}


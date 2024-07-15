package com.example.tamagochi.eating;

public class FoodDestination {
    private int x, y;
    private boolean isVisible;

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public FoodDestination(int x, int y) {
        this.x = x;
        this.y = y;
        this.isVisible = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

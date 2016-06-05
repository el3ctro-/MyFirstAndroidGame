package com.example.electr.myfirstgame;

import android.graphics.Rect;

/**
 * Created by electr on 5/21/2016.
 */
public abstract class GameObject {
    protected int x;
    protected int y;
    protected int dy;
    protected int dx;
    protected int width;
    protected int height;

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX(int x)
    {
        return x;
    }

    public int getY(int y)
    {
        return y;
    }
    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public Rect getRectangle()
    {
        return new Rect(x,y,x+width,y+height);
    }
}

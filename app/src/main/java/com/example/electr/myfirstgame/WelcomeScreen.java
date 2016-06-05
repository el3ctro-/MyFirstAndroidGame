package com.example.electr.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by electr on 5/22/2016.
 */
public class WelcomeScreen {

    private Bitmap image;
    private int x, y, dx;

    public WelcomeScreen(Bitmap res)
    {
        image = res;
        dx = GamePanel.MOVESPEED;
    }
    public void update()
    {

    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, x, y,null);
        if(x<0)
        {
            canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
        }
    }

}
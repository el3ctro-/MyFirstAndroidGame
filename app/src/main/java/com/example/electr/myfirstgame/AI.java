package com.example.electr.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by electr on 5/22/2016.
 */
public class AI extends GameObject {
    private Bitmap spritesheet;
    private float scaleFactorY;
    private float scaleFactorX;

    private Animation animation = new Animation();
    private int score;
    private long startTime;

    public AI(Bitmap res, int w, int h, int numFrames)
    {
        spritesheet = res;
/*
        animation = new Animation();
        x = 100;
        y = GamePanel.HEIGHT/2;
        dy = 0;
        score = 0;
        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames];

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(res,i*width, 0, width, height);
        }
        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();
        */
    }

    public void update(GameBall ball)
    {
        x= ball.maxWidth-50;
        y = ball.ballRect.bottom;
        //y=200 * (int)scaleFactorY;

    }
    public void draw(Canvas canvas)
    {

        canvas.drawBitmap(spritesheet,x,y,null);
    }

    public void setUpAI(GamePanel panel) {
        this.scaleFactorY = panel.getHeight()/(float)panel.HEIGHT;
        this.scaleFactorX = panel.getWidth()/(float)panel.WIDTH;
    }
}

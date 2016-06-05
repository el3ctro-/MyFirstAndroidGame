package com.example.electr.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;

/**
 * Created by electr on 5/21/2016.
 */
public class Player extends GameObject {
    private Bitmap spritesheet;
    private int score;
    public Rect playerRect;
    private double dya;
    private boolean playing = false;
    private boolean up;
    private float scaleFactorX;
    private float scaleFactorY;
    private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames)
    {
        spritesheet = res;
        this.playerRect = new Rect();
        playerRect.set(0,0,w,h);
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
        x=0;
        y=0;
    }

    public void setUp(boolean b) { up = b; }

    public void update()
    {
        /*
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if (elapsed>100)
        {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

        if (up) {
            dy = (int) (dya-=.5);
        } else {
            dy = (int) (dya+=.5);
        }
        if (dy>14) dy = 14;
        if (dy<-14) dy = -14;

        y += dy*2;

        dy = 0;
*/

        if (y < 0) {
            y = 0;
        }
        if (y > scaleFactorY * GamePanel.HEIGHT) {
            y = (int) (scaleFactorY * GamePanel.HEIGHT);
        }
    }

    public void setPosition(int y)
    {

        this.playerRect.top = y;
    }

    public boolean getPlaying() {
        return playing;
    }


    public void draw(Canvas canvas)
    {

        canvas.drawBitmap(spritesheet,playerRect.left,playerRect.top,null);
    }
    public int getScore(){return score;}
    public boolean getPlayer(){return playing;}
    public void setUpPlayer(boolean b, GamePanel panel)
    {
        playing = b;
        //this.scaleFactorX = panel.getWidth()/(float)panel.WIDTH;
        this.scaleFactorY = panel.getHeight()/(float)panel.HEIGHT;    }

    public void setPlaying(boolean b) { playing = b; }
    public void resetSCore() { score = 0;}
}


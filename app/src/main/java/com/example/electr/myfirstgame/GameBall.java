package com.example.electr.myfirstgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * Created by el3ctro on 6/4/16.
 */
public class GameBall {
    private Paint paint = new Paint();
    public int x1, x2, y1, y2, maxWidth, maxHeight;
    public Rect ballRect;
    private String moveState;
    private int playerOn;
    public GameBall(Context context, int maxWidth, int maxHeight)
    {

        //DisplayMetrics dm = new DisplayMetrics();
        //((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        //maxHeight = dm.heightPixels;
        //maxWidth = dm.widthPixels;
        //int w = dm.widthPixels;
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        ballRect = new Rect();
        ballRect.set(20,0,50,35);
        x1 = 20;
        y1 = 0;
        x2 = 50;
        y2 = 35;
       // x1 = maxWidth-20;
       // y1 = maxHeight-0;
       // x2 = maxWidth-50;
       // y2 = maxHeight-35;
        System.out.println(maxWidth);
        moveState = "DOWN";
        playerOn = 1;

    }

    public void update(Player player, AI ai)
    {
        //System.out.println("y1:");
        //System.out.println(y1);
        //System.out.println("x1:");
        //System.out.println(x1);
        // If it reaches the bottom of the screen, move up
        if (ballRect.bottom >= maxHeight) {

        moveState = "UP";
        }
        // if it reaches the top of the screen, move down
        if (ballRect.top <= 0) {
            moveState = "DOWN";
        }

        if (Rect.intersects(player.playerRect,ballRect)) {
            System.out.println("intersect");
            playerOn = 1;
        }

        // if it gets to the left side
        //if (x1 <= 20 && y1 <= player.y && y2 >= player.y) {
        //    System.out.println("score");
        //}
        // If it reaches the right edge of the screen, bounce it upwards
        if (ballRect.right > maxWidth) {
            playerOn = 2;
            if (moveState == "DOWN"){
                moveState = "UP";
            }
        }
        if (moveState == "DOWN") {

            if (playerOn == 1) {
                ballRect.left += 1;
                ballRect.right += 1;
                ballRect.bottom += 3;
                ballRect.top += 3;
            } else if (playerOn == 2) {
                ballRect.left -= 1;
                ballRect.right -= 1;
                ballRect.bottom += 3;
                ballRect.top += 3;
            }
        } else if (moveState == "UP") {
            if (playerOn == 1) {
                ballRect.left += 1;
                ballRect.right += 1;
                ballRect.bottom -= 3;
                ballRect.top -= 3;
            } else if (playerOn == 2) {
                ballRect.left -= 1;
                ballRect.right -= 1;
                ballRect.bottom -= 3;
                ballRect.top -= 3;
            }
        }

    }

    public void setUpBall(GamePanel panel) {
        this.maxHeight = panel.HEIGHT;
        this.maxWidth = panel.WIDTH;
    }

    public void draw(Canvas canvas)
    {

     canvas.drawRect(ballRect.left,ballRect.top,ballRect.right,ballRect.bottom, paint);

    }
}

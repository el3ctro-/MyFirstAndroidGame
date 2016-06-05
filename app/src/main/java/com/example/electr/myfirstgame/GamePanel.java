package com.example.electr.myfirstgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by electr on 5/15/2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    public static final int MOVESPEED = -5;

    private MainThread thread;
    private Background bg;
    private AI ai;
    private Player player;
    private GameBall ball;
    private Context myContext;
    private WelcomeScreen welcome;
    public GamePanel(Context context)
    {
        super(context);
        myContext = context;
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        // make gamePanel focusable so it can handle events
        setFocusable(true);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry)
        {
            try
            {
                thread.setRunning(false);
                thread.join();

            }
            catch(InterruptedException e)
            { e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Display welcome screen
        welcome = new WelcomeScreen(BitmapFactory.decodeResource(getResources(), R.drawable.pong_start_screen));
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.black_screen));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.player_line), 65, 25, 3);
        player.setUpPlayer(false, this);
        ai = new AI(BitmapFactory.decodeResource(getResources(), R.drawable.player_line), 65,25,3);
        ball = new GameBall(myContext, WIDTH, HEIGHT);
        ai.setUpAI(this);
        ball.setUpBall(this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        /*
            if (event.getAction()==MotionEvent.ACTION_DOWN) {
                if (player.getPlaying() == false)
                {
                    player.setPlaying(true);
                }
                else
                {
                    player.setUp(true);
                }
                return true;
            }
            if(event.getAction()==MotionEvent.ACTION_UP) {
                player.setUp(false);
            }*/

       // System.out.println("Touch event");
        if (!player.getPlaying()) {
            System.out.println("touch outside of game detected");
            final float scaleFactorX = getWidth()/(float)WIDTH;
            final float scaleFactorY = getHeight()/(float)HEIGHT;
            if (event.getAction() >= 0) {

                    player.setPlaying(true);
                    System.out.println("Setting game up");
                    return true;
            }
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: // gets called
                {
                    System.out.println("Action Down");
                    return true;
                    //Pressed down
                }
                case MotionEvent.ACTION_MOVE: // doesnt seem to do anything
                {
                   // System.out.println("Action move");

                    player.setPosition((int) event.getY());
                    return true;
                }
            }

        }
       return super.onTouchEvent(event);
    }

    public void update() {


        if (!player.getPlaying()) {

            welcome.update();
        } else {

            bg.update();
            player.update();
            ball.update(player, ai);
            ai.update(ball);
        }

    }
    @Override
    public void draw(Canvas canvas)
    {

        final float scaleFactorX = getWidth()/(float)WIDTH;
        final float scaleFactorY = getHeight()/(float)HEIGHT;
        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);


               // System.out.println("printing welcome");


            if (!player.getPlaying()) {

                welcome.draw(canvas);

            } else {

                bg.draw(canvas);
                player.draw(canvas);
                ai.draw(canvas);
                ball.draw(canvas);

            }

            canvas.restoreToCount(savedState);


        }

    }

    public float getScaleFactor() {
        final float scaleFactorY = getHeight()/(float)HEIGHT;
        return scaleFactorY;
    }
}

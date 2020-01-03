package main;

import entities.Ball;
import math.Vector2D;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private Ball ball;
    private Thread thread;
    private boolean running;
    private int fps = 60;
    private long msPerFrame = 1000/fps;
    // method chain:
    //  GamePanel()
    //  JPanel()
    //  addNotify()
    //  start()
    //  run()
    //  init()
    //  update()
    //  paintComponent()

    private void update() {
        ball.update();
    }

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    private void init() {
        ball = new Ball( 100, 100, 10);
        Vector2D displacement = new Vector2D(2,3);
        ball.setDisplacement(displacement);
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        running = true;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // assumption: will be called with every repaint() at the end of run()
        if(ball != null) ball.draw((Graphics2D) g);
    }

    public void run() {
        long start;
        long sinceStart;
        long untilFramesUp;
        init();
        while(running) {
            start = System.nanoTime();
            update();
            sinceStart = System.nanoTime() - start;
            untilFramesUp = msPerFrame - sinceStart / 1000000;
            if(untilFramesUp < 0)   untilFramesUp = 5;
            try {
                Thread.sleep(untilFramesUp);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}

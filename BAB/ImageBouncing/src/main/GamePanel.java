package main;

import entities.Ball;
import math.Vector2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private BufferedImage background;
    private int fps = 60;
    private int timePerFrame = 1000 / fps;
    private boolean running;
    private Thread thread;
    private Ball ball;
    // methods: GamePanel(), addNotify(), update(), paintComponent(), init(),  run()

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        running = true;
    }

    private void init() {
        Vector2D displacementOfBall = new Vector2D(2,3);
        ball = new Ball(100,100,25,displacementOfBall);
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void update() {
        ball.update();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(background != null) {
            g.drawImage(background,0,0,null);
        }
        if(ball != null) { ball.draw((Graphics2D) g); }
    }

    public void run() {
        init();
        long start;
        long updateTime;
        long timeLeftForFrame; // must be in milliseconds not nanoseconds
        while(running) { // currently the program doesn't stop running
            start = System.nanoTime();
            update();
            updateTime = System.nanoTime() - start;
            timeLeftForFrame = timePerFrame - updateTime / 1000000;
            if(timeLeftForFrame < 0) { timeLeftForFrame = 5; }
            try {
                Thread.sleep(timeLeftForFrame);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}

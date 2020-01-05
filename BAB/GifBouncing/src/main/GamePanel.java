package main;

import entities.Ball;
import math.Vector2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private BufferedImage backGround;
    private Ball ball;
    private Thread thread;
    private boolean running;
    private int fps = 60;
    private long timePerFrame = 1000 / fps;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
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

    public void init() {
        try {
            backGround = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Vector2D displacement = new Vector2D(2,3);
        ball = new Ball(100,100,displacement);
    }

    public void run() {
        init();
        long start;
        long sinceStart;
        long untilFrameFinishes;
        while(running) {
            start = System.nanoTime();
            update();
            sinceStart = System.nanoTime() - start;
            untilFrameFinishes = timePerFrame - sinceStart / 1000000;
            if(untilFrameFinishes < 0) untilFrameFinishes = 5;
            try {
                Thread.sleep(untilFrameFinishes);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            update();
            repaint();
        }
    }

    private void update() {
        ball.update();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(backGround != null) { g.drawImage(backGround,0,0,null); }
        if(ball != null) { ball.draw((Graphics2D) g); }
    }
}

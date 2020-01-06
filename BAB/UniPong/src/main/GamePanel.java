package main;

import entities.Ball;
import math.Vector2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    // methods : keyTyped, keyPressed, keyReleased
    // GamePanel, addNotify, Run, Init, Update, paintComponent
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private Ball ball;
    private BufferedImage background;
    private Thread thread;
    private int fps = 120;
    private long msPerFrame = 1000 / fps;
    private boolean running = false;


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
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        ball = new Ball(0,0,new Vector2D(2,3));
    }

    public void run() {
        init();
        long start;
        long sinceStart;
        long untilFramesUp;
        while (running) {
            start = System.nanoTime();
            update();
            sinceStart = System.nanoTime() - start;
            untilFramesUp = msPerFrame - sinceStart / 1000000;
            if(untilFramesUp < 0) untilFramesUp = 5;
            try {
                Thread.sleep(untilFramesUp);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            repaint();
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
        if(ball != null) {
            ball.drawBall((Graphics2D) g);
        }
    }

    public void keyTyped(KeyEvent keyEvent) {

    }

    public void keyPressed(KeyEvent keyEvent) {

    }

    public void keyReleased(KeyEvent keyEvent) {

    }
}

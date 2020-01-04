package main;

import entities.AnimatedGIF;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private AnimatedGIF gif;
    private Thread thread;
    private boolean running;
    private int fps = 10;
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
    }

    private void init() {
        gif = new AnimatedGIF(0,0);
        gif.readGif("/images/trees.gif");
        running = true;
    }

    public void run() {
        init();
        long start;
        long sinceStart;
        long wait = 100;
        while(running) {
            start = System.nanoTime();

            sinceStart = System.nanoTime() - start;
            wait = timePerFrame - sinceStart / 1000000;
            if(wait < 0) wait = 5;
            try {
                Thread.sleep(wait);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("but did i get here");
        gif.nextFrame();
        System.out.println("and here too...");
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        gif.draw(g2);
    }
}

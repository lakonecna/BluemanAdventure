package main;

import entities.Ball;
import entities.Overlap2DDecider;
import entities.Pinger;
import math.Vector2D;
import states.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    // methods : keyTyped, keyPressed, keyReleased
    // GamePanel, addNotify, Run, Init, Update, paintComponent
    private JFrame myFrame;
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private Controller control;
    //private Ball ball;
    //private Pinger leftPinger;
    //private Pinger rightPinger;
    //private BufferedImage background;
    private Thread thread;
    public static int fps = 60;
    private long msPerFrame = 1000 / fps;
    private boolean running = false;


    public GamePanel(JFrame frame) {
        super();
        myFrame = frame;
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void restart() {
        /*thread = null;
        if(thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
        //running = true;
        ball.restart();
        //run();
         */
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
        running = true;
    }

    private void init() {
        /*
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        ball = new Ball(WIDTH / 2,HEIGHT / 2,new Vector2D(2,3));
        leftPinger = new Pinger(20,true);
        rightPinger = new Pinger(20,false);
        */
        control = new Controller();
        control.init();
    }

    public void run() {
        init();
        long start;
        long sinceStart;
        long untilFramesUp;
        while (control.isRunning()) {
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
        exit();
    }

    private void update() {
        /*
        ball.update(leftPinger);
        ball.update(rightPinger);
        //if(!ball.isBouncing()) { running = false; }
         */
        control.update();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(control != null) {
            //System.out.println("Hello there!");
            control.paint((Graphics2D) g);
        }
    }

    public void keyTyped(KeyEvent k) {
        // will this be needed?
    }

    public void keyPressed(KeyEvent k) {
        /*
        if(k.getKeyCode() == KeyEvent.VK_UP) {
            rightPinger.moveUp();
        }
        if(k.getKeyCode() == KeyEvent.VK_DOWN) {
            rightPinger.moveDown();
        }
        if(k.getKeyCode() == KeyEvent.VK_W) {
            leftPinger.moveUp();
        }
        if(k.getKeyCode() == KeyEvent.VK_S) {
            leftPinger.moveDown();
        }
        if(k.getKeyCode() == KeyEvent.VK_R) {
            restart();
        }*/
        control.keyPressed(k.getKeyCode());
    }

    public void keyReleased(KeyEvent k) {
        // will this be needed?
        control.keyReleased(k.getKeyCode());
    }

    public void exit() {
        myFrame.setVisible(false);
        myFrame.dispose();
        System.exit(0);
    }
}

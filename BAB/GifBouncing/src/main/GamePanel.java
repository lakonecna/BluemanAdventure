package main;

import entities.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private BufferedImage backGround;
    private Ball ball;
    private Thread thread;
    private boolean running;
    private int fps = 30;
    private long timePerFrame = 1000 / fps;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }
}

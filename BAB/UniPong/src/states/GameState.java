package states;

import entities.Ball;
import entities.Pinger;
import math.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GameState extends State {
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private Controller control;
    private Ball ball;
    private Pinger leftPinger;
    private Pinger rightPinger;
    private BufferedImage background;
    //private Thread thread;
    //private int fps = 80;
    //private long msPerFrame = 1000 / fps;
    //private boolean running = false;

    public GameState(Controller control) {
        this.control = control;
        init();
    }

    public void init() {
            try {
                background = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            ball = new Ball(WIDTH / 2,HEIGHT / 2,new Vector2D(2,3));
            leftPinger = new Pinger(20,true);
            rightPinger = new Pinger(20,false);
    }

    // TODO ball shouldn't need to update twice
    public void update() {
        if(ball.canBallBounceLegally()) {
            ball.update(leftPinger);
            ball.update(rightPinger);
        }
        else {
            try {
                Thread.sleep(3000);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            control.gameRequestsGameOver();
        }
    }

    public void paint(Graphics2D g) {
        if(background != null) {
            g.drawImage(background,0,0,null);
        }
        if(ball != null) {
            ball.drawBall((Graphics2D) g);
        }
        if(leftPinger != null) {
            leftPinger.drawPinger((Graphics2D) g);
        }
        if(rightPinger != null) {
            rightPinger.drawPinger((Graphics2D) g);
        }
    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_UP) {
            rightPinger.moveUp();
        }
        if(k == KeyEvent.VK_DOWN) {
            rightPinger.moveDown();
        }
        if(k == KeyEvent.VK_W) {
            leftPinger.moveUp();
        }
        if(k == KeyEvent.VK_S) {
            leftPinger.moveDown();
        }
        if(k == KeyEvent.VK_R) {
            restart();
        }
    }

    public void keyReleased(int keyCode) {
        //??
    }

    private void restart() {
        ball.restart();
    }
}

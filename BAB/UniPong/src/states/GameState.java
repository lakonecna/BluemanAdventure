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
    private Ball ball;
    private Pinger leftPinger;
    private Pinger rightPinger;
    private BufferedImage background;
    //private Thread thread;
    //private int fps = 80;
    //private long msPerFrame = 1000 / fps;
    //private boolean running = false;

    public GameState() {

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

    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics2D g) {

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
        // How do we get the ball to reappear without speeding up?
    }
}

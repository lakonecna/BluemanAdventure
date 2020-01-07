package entities;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Pinger {
    private Point position;
    private int width;
    private int height;
    private BufferedImage pinger;
    public static final int fromWall = 20;

    public Pinger() {
        position = new Point();
        position.x = fromWall;
        position.y = GamePanel.HEIGHT / 2 - height / 2 - 1;
        init();
    }
    public void setPosition(Point position) { this.position = position; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public Point getPosition() { return position; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    private void init() {
        try {
            pinger = ImageIO.read(getClass().getResourceAsStream("pinger.png"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        if (pinger != null) {
            width = pinger.getWidth();
            height = pinger.getHeight();
        }
    }

    public void moveDown() {
        position.y += 2;
        possiblyBounce();
    }

    public void moveUp() {
        position.y -= 2;
        possiblyBounce();
    }

    public void update() {
        // is this needed
    }

    private void possiblyBounce() {
        if(position.y < 0) {
            position.y = 0;
        }
        if(position.y > GamePanel.HEIGHT - height - 1) {
            position.y = GamePanel.HEIGHT - height - 1;
        }
    }

    public boolean hitsBall(Ball ball) {
        //TODO : does as expected
        return false;
    }

    public void drawPinger(Graphics2D g) {
        if(pinger != null) {
            g.drawImage(pinger,position.x,position.y,null);
        }
    }
}

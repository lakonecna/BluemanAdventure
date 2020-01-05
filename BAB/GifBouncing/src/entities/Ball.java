package entities;

import main.GamePanel;
import math.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ball {
    private Point point;
    private int radius;
    private Vector2D displacement;
    private AnimatedGIF gif;

    public Ball(int x, int y, Vector2D displacement) {
        point = new Point(x,y);
        this.displacement = displacement;
        init();
        this.radius = gif.imageWIDTH / 2;
    }

    public void setX(int x) { point.x = x; }

    public void setY(int y) { point.y = y; }

    public void setRadius(int radius) { this.radius = radius; }

    public void setDisplacement(Vector2D displacement) { this.displacement = displacement; }

    public int getX() { return point.x; }

    public int getY() { return point.y; }

    public int getRadius() { return radius; }

    public Vector2D getDisplacement() { return displacement; }

    private void init() {
       /* try {
            image = ImageIO.read(getClass().getResourceAsStream("/rotatingBallAnimation/image (0).png"));
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }*/
       gif = new AnimatedGIF(point.x,point.y);
       gif.readGIF("/ball/ball.gif");

    }

    public void update() {
        point.x += displacement.getX();
        point.y += displacement.getY();
        possiblyBounce();
        gif.setPoint(point.x,point.y);
    }

    public void possiblyBounce() {
        if(point.x < 0) {
            point.x = 0;
            displacement.setX(-displacement.getX());
        }
        if(point.x > GamePanel.WIDTH - (2 * radius) - 1) {
            point.x = GamePanel.WIDTH - (2 * radius) - 1;
            displacement.setX(-displacement.getX());
        }
        if(point.y < 0) {
            point.y = 0;
            displacement.setY(-displacement.getY());
        }
        if(point.y > GamePanel.HEIGHT - (2 * radius) - 1) {
            point.y = GamePanel.HEIGHT - (2 * radius) - 1;
            displacement.setY(-displacement.getY());
        }
    }

    public void nextFrame() {
        gif.nextFrame();
    }

    public void draw(Graphics2D g) {
        if(gif != null) {
            gif.drawFrame(g);
        }
    }

}

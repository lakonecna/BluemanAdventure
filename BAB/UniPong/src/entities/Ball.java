package entities;

import main.GamePanel;
import math.Vector2D;

import java.awt.*;

public class Ball {
    private Point position;
    private int radius;
    private Vector2D displacement;
    private AnimatedGIF gif;

    public Ball(int x,int y, Vector2D displacement) {
        position = new Point(x,y);
        this.displacement = displacement;
        init();
    }

    public int getX() { return position.x; }
    public int getY() { return position.y; }
    public int getRadius() {return radius;}
    public Vector2D getDisplacement() { return displacement; }
    public void setX(int x) { position.x = x; }
    public void setY(int y) { position.y = y; }
    public void setRadius(int radius) { this.radius = radius; }
    public void setDisplacement(Vector2D displacement) { this.displacement = displacement; }

    public void init() {
        gif = new AnimatedGIF(position);
        gif.readGIF("/ball/ball.gif");
        radius = gif.getImageWIDTH() / 2;
    }

    public void update() {
        position.x += displacement.getX();
        position.y += displacement.getY();
        possiblyBounce();
        if(gif != null) {
            gif.setPosition(position);
            gif.nextFrame();
        }
    }

    //TODO later//////////////////////////////////////////////////////
    private void possiblyBounceFromPinger(Pinger pinger) {
        if(position.x == pinger.getPosition().x) {
            if(pinger.hitsBall(this)) {
                //...
            } // is it along the edge of pinger
        }
    } /////////////////////////////////////////////////////////////////

    private void possiblyBounce() {
        if(position.x < 0) {
            position.x = 0;
            displacement.setX(-displacement.getX());
        }
        if(position.x > GamePanel.WIDTH - (2 * radius) - 1) {
            position.y = GamePanel.WIDTH - (2 * radius) - 1;
            displacement.setX(-displacement.getX());
        }
        if(position.y < 0) {
            position.y = 0;
            displacement.setY(-displacement.getY());
        }
        if(position.y > GamePanel.HEIGHT - (2 * radius) - 1) {
            position.y = GamePanel.HEIGHT - (2 * radius) - 1;
            displacement.setY(-displacement.getY());
        }
    }

    public void drawBall(Graphics2D g) {
        if(gif != null) {
            gif.draw(g);
        }
    }
}

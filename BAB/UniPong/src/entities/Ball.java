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
    public AnimatedGIF getGif() { return gif; }
    public void setX(int x) { position.x = x; }
    public void setY(int y) { position.y = y; }
    public void setRadius(int radius) { this.radius = radius; }
    public void setDisplacement(Vector2D displacement) { this.displacement = displacement; }
    public void setGif(AnimatedGIF gif) { this.gif = gif; }

    public void init() {
        gif = new AnimatedGIF(position);
        gif.readGIF("/ball/ball.gif");
        radius = gif.getImageWIDTH() / 2;
    }

    public void update(Pinger pinger) {
        position.x += displacement.getX();
        position.y += displacement.getY();
        possiblyBounceFromWall();
        if(pingerHitsBall()) {
            bounceFromPinger(pinger);
        }
        if(gif != null) {
            gif.setPosition(position);
            gif.nextFrame();
        }
    }

    //TODO later//////////////////////////////////////////////////////
    private void bounceFromPinger(Pinger pinger) {
        
    }

    private void possiblyBounceFromWall() {
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

    private boolean pingerHitsBall(Pinger pinger) {
        //TODO
        // if the ball's rectangle overlaps with the pinger's
        // return true, else false
        // later update so true is only returned if
        // there is overlap between
        // pinger's rectangle and ball's sphere
        Overlap2DDecider decider = new Overlap2DDecider(getCorners(), pinger.getCorners());
        return decider.getOverlapping();
    }

    private Point[] getCorners() {
        Point[] corners = new Point[4];
        corners[0] = new Point(position);
        corners[1] = new Point(position.x,position.y + radius * 2);
        corners[2] = new Point(position.x + radius * 2,position.y);
        corners[3] = new Point(position.x + radius * 2,position.y + radius * 2);
        return corners;
    }
}

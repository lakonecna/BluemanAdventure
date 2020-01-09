package entities;

import main.GamePanel;
import math.Vector2D;

import java.awt.*;

import static java.lang.Math.abs;

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
        if(pingerHitsBall(pinger)) {
            bounceFromPinger(pinger);
        }
        if(gif != null) {
            gif.setPosition(position);
            gif.nextFrame();
        }
    }

    //TODO later//////////////////////////////////////////////////////
    private void bounceFromPinger(Pinger pinger) {
        // possible method: find the distance of ball center from
        // the ends of each wall, find average for each wall
        // smallest is the one that must be touching the wall
        // if bottom or top, invert the y component of vector
        // if side, invert the x component of the vector
        Point[] corners = pinger.getCorners();
        double[] distances = new double[4];
        double d1;
        double d2;
        Point ballCenter = new Point(position.x + radius,position.y + radius);

        // average distance from north wall (elements 0 and 2)
        d1 = abs((ballCenter.y - corners[0].y) / (ballCenter.x - corners[0].x));
        d2 = abs((ballCenter.y - corners[2].y) / (ballCenter.x - corners[2].x));
        distances[0] = (d1 + d2) / 2;

        // average distance from east wall (elements 2 and 3)
        d1 = abs((ballCenter.y - corners[3].y) / (ballCenter.x - corners[3].x));
        // d2 = abs((ballCenter.y - corners[2].y) / (ballCenter.x - corners[2].x));
        distances[1] = (d1 + d2) / 2;

        // average distance from south wall (elements 1 and 3)
        // d1 = abs((ballCenter.y - corners[3].y) / (ballCenter.x - corners[3].x));
        d2 = abs((ballCenter.y - corners[1].y) / (ballCenter.x - corners[1].x));
        distances[2] = (d1 + d2) / 2;

        // average distance from west wall (elements 0 and 1)
        d1 = abs((ballCenter.y - corners[0].y) / (ballCenter.x - corners[0].x));
        // d2 = abs((ballCenter.y - corners[1].y) / (ballCenter.x - corners[1].x));
        distances[3] = (d1 + d2) / 2;

        // optionally cases when corners of pinger are reflecting can also be covered,
        // in such a case (where a corner is on the circumference of the ball)
        // both of the balls vectors components can be inverted
        // TODO

        // get minimum distance wall (from ball center)
        double minDistance = getMinElement(distances);
        // depending on which dist is shortest, invert component of
        // displacement vector
        if(distances[0] == minDistance) {
            displacement.setY(-displacement.getY());
        }
        else 
    }

    private double getMinElement(double[] array) {
        double curMin = array[0];
        for (double d :
                array) {
            if (d < curMin) {
                curMin = d;
            }
        }
        return curMin;
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

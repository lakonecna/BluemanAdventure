package entities;

import main.GamePanel;
import math.Vector2D;

import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class Ball {
    private Point position;
    private Point prevPosition;
    private int radius;
    private Vector2D displacement;
    private AnimatedGIF gif;
    private boolean isBouncing = false;

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
    public Point getPrevPosition() { return prevPosition; }
    public boolean isBouncing() { return isBouncing; }
    public void setX(int x) { position.x = x; }
    public void setY(int y) { position.y = y; }
    public void setRadius(int radius) { this.radius = radius; }
    public void setDisplacement(Vector2D displacement) { this.displacement = displacement; }
    public void setGif(AnimatedGIF gif) { this.gif = gif; }
    public void setPrevPosition(Point prevPosition) { this.prevPosition = prevPosition; }
    private void isBouncing(boolean isBouncing) { this.isBouncing = isBouncing; }

    public void init() {
        gif = new AnimatedGIF(position);
        gif.readGIF("/ball/ball.gif");
        radius = gif.getImageWIDTH() / 2;
        isBouncing = true;
    }

    public void update(Pinger pinger) {
        prevPosition = new Point(position.x,position.y);
        position.x += displacement.getX();
        position.y += displacement.getY();
        System.out.println("PrevPos:" + prevPosition.x + "_" + prevPosition.y + "New pos:" + position.x + "_" + position.y);
        possiblyBounceFromWall();
        Overlap2DDecider decider = new Overlap2DDecider(getCorners(), pinger.getCorners());
        if(pingerHitsBall(pinger,decider)) {
            System.out.println("pingerHitsBall TRUE");
            System.out.println("Displacement(x,y):" + displacement.getX() + "_" + displacement.getY());
            bounceFromPinger(pinger);
        }
        if(gif != null) {
            gif.setPosition(position);
            gif.nextFrame();
        }
    }

    private void bounceFromPinger(Pinger pinger) {
        // possible method: find the distance of ball center from
        // the ends of each wall, find average for each wall
        // smallest is the one that must be touching the wall
        // if bottom or top, invert the y component of vector
        // if side, invert the x component of the vector
        Point ballCenter = new Point(position.x + radius,position.y + radius);
        Point[] corners = pinger.getCorners();
        double[] distances = new double[4];
        double d1;
        double d2;

        // average distance from north wall (elements 0 and 2)
        d1 = abs((ballCenter.y - corners[0].y) / notZero(ballCenter.x - corners[0].x));
        d2 = abs((ballCenter.y - corners[2].y) / notZero(ballCenter.x - corners[2].x));
        distances[0] = (d1 + d2) / 2;

        // average distance from east wall (elements 2 and 3)
        d1 = abs((ballCenter.y - corners[3].y) / notZero(ballCenter.x - corners[3].x));
        distances[1] = (d1 + d2) / 2;

        // average distance from south wall (elements 1 and 3)
        d2 = abs((ballCenter.y - corners[1].y) / notZero(ballCenter.x - corners[1].x));
        distances[2] = (d1 + d2) / 2;

        // average distance from west wall (elements 0 and 1)
        d1 = abs((ballCenter.y - corners[0].y) / notZero(ballCenter.x - corners[0].x));
        distances[3] = (d1 + d2) / 2;

        // optionally cases when corners of pinger are reflecting can also be covered,
        // in such a case (where a corner is on the circumference of the ball)
        // both of the balls vectors components can be inverted
        // TODO

        double minDistance = getMinElement(distances);

        if(distances[0] == minDistance) { //N
            ///position.y = pinger.getNorthLimit();
            System.out.println("north:" + position.y);
            displacement.setY(-displacement.getY());
            System.out.println("Displacement changed by NPing:" + displacement.getX() + "_" + displacement.getY());
        }
        else if(distances[0] == minDistance && distances[1] == minDistance) {
            //position.x = pinger.;
            //position.y =
            System.out.println("Hitting NE");
            displacement.setX(-displacement.getX());
            displacement.setY(-displacement.getY());
            System.out.println("Displacement changed by NEPing:" + displacement.getX() + "_" + displacement.getY());
        }
        else if(distances[1] == minDistance) { //E
            ///position.x = pinger.getEastLimit();
            System.out.println("east:" + position.x);
            displacement.setX(-displacement.getX());
            System.out.println("Displacement changed by EPing:" + displacement.getX() + "_" + displacement.getY());
        }
        else if(distances[1] == minDistance && distances[2] == minDistance) {
            //position.x = pinger.;
            //position.y =
            System.out.println("Hitting SE");
            displacement.setX(-displacement.getX());
            displacement.setY(-displacement.getY());
            System.out.println("Displacement changed by SEPing:" + displacement.getX() + "_" + displacement.getY());
        }
        else if(distances[2] == minDistance) { //S
            ///position.y = pinger.getSouthLimit();
            System.out.println("south:" + position.y);
            displacement.setY(-displacement.getY());
            System.out.println("Displacement changed by SPing:" + displacement.getX() + "_" + displacement.getY());
        }
        else if(distances[2] == minDistance && distances[3] == minDistance) {
            //position.x = pinger.;
            //position.y =
            System.out.println("Hitting SW");
            displacement.setX(-displacement.getX());
            displacement.setY(-displacement.getY());
            System.out.println("Displacement changed by SWPing:" + displacement.getX() + "_" + displacement.getY());
        }
        else if(distances[3] == minDistance) { //W
            ///position.x = pinger.getWestLimit();
            System.out.println("west:" + position.x);
            displacement.setX(-displacement.getX());
            System.out.println("Displacement changed by WPing:" + displacement.getX() + "_" + displacement.getY());
        }
        else if(distances[3] == minDistance && distances[0] == minDistance) {
            //position.x = pinger.;
            //position.y =
            System.out.println("Hitting NW");
            displacement.setX(-displacement.getX());
            displacement.setY(-displacement.getY());
            System.out.println("Displacement changed by NWPing:" + displacement.getX() + "_" + displacement.getY());
        }
        returnToPreviousPosition();
    }

    private boolean possiblyBounceFromWall() {
        boolean bounced = false;
        if(position.x < 0) {
            //position.x = 0;
            //displacement.setX(-displacement.getX());
            bounced = true;
            if(position.x == -(2 * radius)) { isBouncing = false; }
        }
        if(position.x > GamePanel.WIDTH - (2 * radius) - 1) {
            bounced = true;
            position.x = GamePanel.WIDTH - (2 * radius) - 1;
            displacement.setX(-displacement.getX());
            System.out.println("Displacement changed by WWall:" + displacement.getX() + "_" + displacement.getY());
        }
        if(position.y < 0) {
            bounced = true;
            position.y = 0;
            displacement.setY(-displacement.getY());
            System.out.println("Displacement changed by NWall:" + displacement.getX() + "_" + displacement.getY());
        }
        if(position.y > GamePanel.HEIGHT - (2 * radius) - 1) {
            bounced = true;
            position.y = GamePanel.HEIGHT - (2 * radius) - 1;
            displacement.setY(-displacement.getY());
            System.out.println("Displacement changed by SWall:" + displacement.getX() + "_" + displacement.getY());
        }
        return bounced;
    }

    public void drawBall(Graphics2D g) {
        if(gif != null) {
            gif.draw(g);
        }
    }

    private boolean pingerHitsBall(Pinger pinger, Overlap2DDecider decider) {
        return decider.getOverlapping();
    }

    private void returnToPreviousPosition() {
        position = new Point(prevPosition.x,prevPosition.y);
        System.out.println("-In returnToPreviousPosition:");
        System.out.println("-PrevPos:" + prevPosition.x + "_" + prevPosition.y + "New pos:" + position.x + "_" + position.y);
        System.out.println("-Displacement(x,y):" + displacement.getX() + "_" + displacement.getY());
    }

    private Point[] getCorners() {
        Point[] corners = new Point[4];
        corners[0] = new Point(position);
        corners[1] = new Point(position.x,position.y + radius * 2);
        corners[2] = new Point(position.x + radius * 2,position.y);
        corners[3] = new Point(position.x + radius * 2,position.y + radius * 2);
        return corners;
    }

    private double notZero(double number) {
        if(number == 0) { return 0.0000000000001; }
        return number;
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
}

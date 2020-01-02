package entities;

import main.GamePanel;
import math.Vector2D;

import java.awt.*;

public class Ball {
    private Point point;
    private int radius;
    private Vector2D displacement;

    public Ball(int x, int y, int radius) {
        point = new Point( x, y);
        this.radius = radius;
    }

    public void setX(int x) {
        point.x = x;
    }

    public void setY(int y) {
        point.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setDisplacement(Vector2D vector) {
        displacement = vector;
    }

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    public int getRadius() {
        return radius;
    }

    public Vector2D getDisplacement() {
        return displacement;
    }

    public void update() {
        point.x += displacement.getX();
        point.y += displacement.getY();

        if(getX() < 0) {
            setX(0);
            displacement.setX(displacement.getX() * -1);
        }
        if(GamePanel.WIDTH - radius * 2 - 1 < getX()) {
            setX(GamePanel.WIDTH - radius * 2 - 1);
            displacement.setX(displacement.getX() * -1);
        }
        if(getY() < 0) {
            setY(0);
            displacement.setY(displacement.getY() * -1);
        }
        if(GamePanel.HEIGHT - radius * 2 - 1 < getY()) {
            setY(GamePanel.HEIGHT - radius * 2 - 1);
            displacement.setY(displacement.getY() * -1);
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(100,50,120));
        g.fillOval(getX(), getY(), getRadius() * 2, getRadius() * 2);
        g.setColor(Color.BLACK);
        g.drawOval(getX(), getY(), getRadius() * 2, getRadius() * 2);
        g.drawString("Ball Position: (" + getX()+ "," + getY() + ")", 500, 460);
    }
}

package entities;

import java.awt.*;

public class Pinger {
    private Point position;
    private int width;
    private int height;

    public Pinger(Point position) { this.position = position; }
    public void setPosition(Point position) { this.position = position; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public Point getPosition() { return position; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    private void init() {
        //TODO : get photo, get its width/ height and set them to width/ height
    }

    public boolean hitsBall(Ball ball) {
        //TODO : does as expected
        return false;
    }
}

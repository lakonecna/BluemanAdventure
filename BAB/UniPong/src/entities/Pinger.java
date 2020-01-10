package entities;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Pinger {
    private Point position;
    private int width;
    private int height;
    private BufferedImage pinger;
    public static final int fromWall = 20;

    public Pinger() {
        position = new Point();
        init();
        position.x = fromWall ;
        position.y = GamePanel.HEIGHT / 2 - height / 2 - 1 ;
    }
    public void setPosition(Point position) { this.position = position; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public Point getPosition() { return position; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    private void init() {
        try {
            pinger = ImageIO.read(getClass().getResourceAsStream("/pinger.png"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        if (pinger != null) {
            width = pinger.getWidth();
            height = pinger.getHeight();
        }
    }

    public int getNorthLimit() {
        return position.y;
    }

    public int getEastLimit() {
        return position.x + width;
    }

    public int getSouthLimit() {
        return position.y + height;
    }

    public int getWestLimit() {
        return position.x;
    }

    public void moveDown() {
        position.y += 20;
        possiblyBounce();
    }

    public void moveUp() {
        position.y -= 20;
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
            //System.out.println(position.x + "_" + position.y);
        }
    }

    public Point[] getCorners() {
        Point[] corners = new Point[4];
        corners[0] = new Point(position);
        corners[1] = new Point(position.x,position.y + height);
        corners[2] = new Point(position.x + width,position.y);
        corners[3] = new Point(position.x + width,position.y + height);
        return corners;
    }
}

package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    protected void paintComponent(Graphics g) {
        //TODO: paint face
        super.paintComponent(g);
        //bg
        g.setColor(Color.magenta);
        g.fillRect(0,0, WIDTH, HEIGHT);
        //face
        int faceRectPointX = 0;
        int faceRectPointY = 0;
        int faceRectWidth = WIDTH;
        int faceRectHeight = HEIGHT;
        g.setColor(new Color(150,150,150));
        g.fillOval(faceRectPointX,faceRectPointY, faceRectWidth, faceRectHeight);
        g.setColor(Color.black);
        g.drawOval(faceRectPointX, faceRectPointY, faceRectWidth, faceRectHeight);
        //eyes
        int eye1CenterX = (faceRectWidth - faceRectPointX) / 3;
        int eyeCenterY = (faceRectHeight - faceRectPointY) / 3;
        int eye2CenterX = 2 * ( (faceRectWidth - faceRectPointX) / 3 );
        int eyeRadius = 50;
        int eye1RectPointX = eye1CenterX - eyeRadius;
        int eye1RectPointY = eyeCenterY - eyeRadius;
        int eye2RectPointX = eye2CenterX - eyeRadius;
        int eye2RectPointY = eyeCenterY - eyeRadius;
        g.setColor(new Color( 50, 150, 50));
        g.fillOval(eye1RectPointX, eye1RectPointY, eyeRadius, eyeRadius);
        g.fillOval(eye2RectPointX, eye2RectPointY, eyeRadius, eyeRadius);
        // mouth arc

        int mouthCenterX = faceRectWidth / 2;
        int mouthCenterY = 2 * (faceRectHeight / 3);
        int mouthWidth = eyeRadius * 3;
        int mouthHeight = eyeRadius * 2;
        int mouthRectPointX = mouthCenterX - (mouthWidth / 2);
        int mouthRectPointY = mouthCenterY - (mouthHeight / 2);
        /*
        int mouthFromAngle = 210;
        int mouthToAngle = 120;
        g.setColor(Color.black);
        g.drawArc(mouthRectPointX, mouthRectPointY, mouthWidth, mouthHeight, mouthFromAngle, mouthToAngle);
         */
        // polygon mouth
        Polygon mouth = new Polygon();
        int mouthRectPoint2X = mouthCenterX + (mouthWidth / 2);
        mouth.addPoint(mouthRectPointX, mouthRectPointY);
        mouth.addPoint(mouthCenterX, mouthCenterY);
        mouth.addPoint(mouthRectPoint2X, mouthRectPointY);
        g.setColor(Color.black);
        g.fillPolygon(mouth);
    }
}

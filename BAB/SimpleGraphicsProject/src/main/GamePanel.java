package main;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,GamePanel.WIDTH,GamePanel.HEIGHT);
        g.setColor(Color.cyan);
        g.setColor(new Color(250,50,50));
        g.setFont(new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 15));
        String str = "Hello";
        int panelCenterX = WIDTH/2;
        int panelCenterY = HEIGHT/2;
        FontMetrics metrics = g.getFontMetrics();
        Rectangle2D strRect = metrics.getStringBounds(str, g);
        int strStartX = panelCenterX - (int) strRect.getWidth() / 2;
        int strStartY = panelCenterY + (int) strRect.getHeight()/ 2;
        g.drawString(str, strStartX, strStartY);
        g.setColor(new Color(100,150,50));
        g.drawLine(0, HEIGHT/2,WIDTH,HEIGHT/2);
        g.drawLine(100,100,100,100);
        g.setColor(new Color(100,150,200));
        g.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
    }

}

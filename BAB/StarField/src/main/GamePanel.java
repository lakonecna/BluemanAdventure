package main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
        g.setColor(Color.cyan);
        g.fillRect(0,0, WIDTH, HEIGHT);

        Random rand = new Random();
        for(int i = 0; i < 1000; ++i) {
            Color randCol = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            g.setColor(randCol);
            int x = Math.abs(rand.nextInt() % WIDTH);
            int y = Math.abs(rand.nextInt() % HEIGHT);
            System.out.println(x + " " + y);
            g.drawLine( x, y, x, y);
        }
    }
}

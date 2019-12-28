package main;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Hello!", 300, 220);
    }

}

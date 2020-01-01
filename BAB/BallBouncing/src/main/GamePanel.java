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
        init();
    }

    private void init() {
        //TODO
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //TODO: finish
    }
}

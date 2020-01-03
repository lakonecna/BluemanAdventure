package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel {
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    BufferedImage image;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        init();
    }

    public void init() {
        try {
            // issue solution: resource files must be in the module's output folder,
            // the references and the .class files must be in one folder
            image = ImageIO.read(getClass().getResourceAsStream("/forest.jpg"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image != null) {
            g.drawImage(image, 0, 0, null);

        }
    }
}

package main;

import javax.swing.*;

public class ImageBouncing {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("Image Bouncing Programme");
        mainWindow.setContentPane(new GamePanel());
        mainWindow.setResizable(false);
        mainWindow.pack();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }
}

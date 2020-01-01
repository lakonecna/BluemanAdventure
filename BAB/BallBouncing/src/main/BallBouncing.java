package main;

import javax.swing.*;

public class BallBouncing {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("Ball Bouncing Program");
        mainWindow.setContentPane(new GamePanel());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}

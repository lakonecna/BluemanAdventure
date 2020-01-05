package main;

import javax.swing.*;

public class GIFBouncingProgramme {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("Bouncing GIF Programme");
        mainWindow.setContentPane(new GamePanel());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
    }
}

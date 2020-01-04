package main;

import javax.swing.*;

public class AnimatedGIFProgramme {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("AnimatedGIF Programme");
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);
        mainWindow.setContentPane(new GamePanel());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}

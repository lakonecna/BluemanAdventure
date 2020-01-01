package main;

import javax.swing.*;

public class ReadImage {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("Read Image Program");
        mainWindow.setContentPane(new GamePanel());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
    }
}

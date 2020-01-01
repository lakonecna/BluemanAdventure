package main;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        JFrame window = new JFrame("Happy Face Program");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.pack();
        window.setVisible(true);
    }
}

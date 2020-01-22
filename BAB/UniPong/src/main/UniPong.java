package main;

import javax.swing.*;

public class UniPong {
    public static void main(String[] args) {
        JFrame window = new JFrame("UniPong");
        window.setContentPane(new GamePanel(window));
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }


}

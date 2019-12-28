package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Game {
    public static void main(String[] args) {
        JFrame window = new JFrame("Simple Graphics Project");
        window.setContentPane(new GamePanel());
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        System.out.println("Width:_" + window.getWidth() + "_Height_" + window.getHeight());
    }

}

package main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class UniPong {
    private static JFrame window = new JFrame("UniPong");;
    public static void main(String[] args) {
        window.setContentPane(new GamePanel(window));
        window.pack();
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}

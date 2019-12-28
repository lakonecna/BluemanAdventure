package applet;

import javax.swing.*;
import java.awt.*;
import main.GamePanel;

// how to run this in intellij?
public class GameApplet extends JApplet {
    private static final long serialVersionUID = 1L;
    private GamePanel gamePanel;

    public void init() {
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        gamePanel = new GamePanel();
        pane.add(gamePanel);
        this.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
    }
}

package states;

import main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameOverState extends State {
    private Controller control;
    private String text;
    private int msLifetime;

    public GameOverState(Controller control) {
        this.control = control;
        init();
    }

    public void init() {
        text = "Game Over...";
        msLifetime = 10000;
    }

    public void update() {
        try {
            Thread.sleep(msLifetime);
        }
        catch (Exception e) {
            e.printStackTrace();
            Thread.interrupted();
        }
        control.gameOverRequestsMenu();
    }

    public void paint(Graphics2D g) {
        //Bg
        g.setColor(Color.BLACK);
        g.fillRect(0,0, GamePanel.WIDTH,GamePanel.HEIGHT);

        //Welcome message
        g.setColor(new Color(100,200,100));
        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,30));
        FontMetrics welcomeMetrics = g.getFontMetrics();
        Rectangle2D welcomeRect = welcomeMetrics.getStringBounds(text,g);
        int pointX = GamePanel.WIDTH / 2 - (int) welcomeRect.getWidth() / 2;
        int pointY = GamePanel.HEIGHT / 2 - (int) welcomeRect.getHeight() / 2;
        g.drawString(text, pointX,pointY);
    }

    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_ENTER) {
            Thread.interrupted();
            control.gameOverRequestsMenu();
        }
    }

    public void keyReleased(int keyCode) {
        // probably ignore
    }
}

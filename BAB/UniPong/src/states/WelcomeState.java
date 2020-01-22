package states;

import entities.AnimatedGIF;
import main.GamePanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class WelcomeState extends State {
    private Controller control;
    private String text;
    private int sleepTime;
    private int currentTime;

    public WelcomeState(Controller control) {
        this.control = control;
        init();
    }

    public void init() {
        text = "WoosalexGames Presents...";
        sleepTime = 45;
        currentTime = 0;
    }

    public void update() {
        // will this be needed?
        try {
            Thread.sleep(1);
            ++currentTime;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        if(currentTime == sleepTime) {
            control.welcomeRequestsMenu();
        }
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
        // no responses
    }

    public void keyReleased(int keyCode) {
        // no responses
    }
}

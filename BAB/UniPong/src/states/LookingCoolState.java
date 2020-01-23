package states;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class LookingCoolState extends State {
    private Controller control;
    private BufferedImage picture;
    private int sleepTime;
    private int currentTime;

    public LookingCoolState(Controller control) {
        this.control = control;
        init();
    }

    public void init() {
        try {
            picture = ImageIO.read(getClass().getResourceAsStream("/lookcoolimage.jpg"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        sleepTime = 200;
        currentTime = 0;
    }

    public void update() {
        try {
            Thread.sleep(1);
            ++currentTime;
        }
        catch (Exception e) {
            e.printStackTrace();
            //Thread.interrupted();
        }
        if(currentTime == sleepTime) {
            control.lcsRequestsMenu();
        }
    }

    public void paint(Graphics2D g) {
        if(picture != null) {
            g.drawImage(picture,0,0,null);
        }
        /*//Bg
        g.setColor(Color.BLACK);
        g.fillRect(0,0, GamePanel.WIDTH,GamePanel.HEIGHT);

        //Welcome message
        g.setColor(new Color(100,200,100));
        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,30));
        FontMetrics welcomeMetrics = g.getFontMetrics();
        Rectangle2D welcomeRect = welcomeMetrics.getStringBounds(text,g);
        int pointX = GamePanel.WIDTH / 2 - (int) welcomeRect.getWidth() / 2;
        int pointY = GamePanel.HEIGHT / 2 - (int) welcomeRect.getHeight() / 2;
        g.drawString(text, pointX,pointY);*/
    }

    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_ENTER) {
            Thread.interrupted();
            control.lcsRequestsMenu();
        }
    }

    public void keyReleased(int keyCode) {
        // probably ignore
    }
}

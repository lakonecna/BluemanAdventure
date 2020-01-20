package states;

import entities.AnimatedGIF;
import main.GamePanel;
import main.UniPong;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class MenuState extends State {
    private Controller control;
    private AnimatedGIF background;
    private String[] options;
    private int currentOptionIndex;

    public MenuState(Controller control) {
        super();
        options = new String[]{"Play","Look Cool","Exit"};
        currentOptionIndex = 0;
        this.control = control;
        init();
    }

    public void init() {
        background = new AnimatedGIF(new Point(0,0));
        background.readGIF("/menubg.gif");
    }

    public void update() {
        background.nextFrame();
    }

    /*
    public void update(Controller control) {
        background.nextFrame();
    }
     */

    public void paint(Graphics2D g) {
        if(background != null) {
            background.draw(g);
        }
        Font titleFont = new Font(Font.MONOSPACED,Font.BOLD,60);
        g.setFont(titleFont);
        Color titleColor = new Color(100,200,100);
        g.setColor(titleColor);
        FontMetrics titleMetrics = g.getFontMetrics();
        Rectangle2D titleArea = titleMetrics.getStringBounds("UniPong",g);
        int titleWidth = (int) titleArea.getWidth();
        g.drawString("UniPong",GamePanel.WIDTH / 2 - titleWidth / 2, 100);

        Font choicesFont = new Font(Font.MONOSPACED,Font.PLAIN,40);
        Font choiceFont = new Font(Font.MONOSPACED,Font.BOLD,50);
        Color choicesColor = new Color(50,50,50);
        Color choiceColor = new Color(100,100,200);
        for(int i = 0; i < options.length; ++i) {
            if(i == currentOptionIndex) {
                g.setFont(choiceFont);
                g.setColor(choiceColor);
            }
            else {
                g.setFont(choicesFont);
                g.setColor(choicesColor);
            }
            g.drawString(options[i],10,390 + i * 40);
        }

    }

    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_DOWN) {
            ++currentOptionIndex;
        }
        else if(keyCode == KeyEvent.VK_UP) {
            --currentOptionIndex;
        }
        else if(keyCode == KeyEvent.VK_ENTER) {
            if(currentOptionIndex == 0) {
                control.menuRequestsGame();
            }
            else if(currentOptionIndex == 1) {
                // nothing here
            }
            else if(currentOptionIndex == 2) {
                control.menuRequestsExit();
            }
        }
        updateCurrentOption();
    }

    public void keyReleased(int keyCode) {
        // Probably won't be necessary
    }

    private void updateCurrentOption() {
        if(currentOptionIndex < 0) {
            currentOptionIndex = 2;
        }
        else if(currentOptionIndex == 3) {
            currentOptionIndex = 0;
        }
    }
}

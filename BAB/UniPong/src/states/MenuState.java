package states;

import entities.AnimatedGIF;

import java.awt.*;
import java.awt.event.KeyEvent;

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
        /*Font choicesFont = new Font(Font.MONOSPACED,Font.PLAIN,10);
        Font choiceFont = new Font(Font.MONOSPACED,Font.BOLD,11);
        FontMetrics choicesMetrics = new FontMetrics()
        Color choicesColor = new Color(50,50,50);
        Color choiceColor = new Color(100,100,100);
        for (String i : options) {
            g.drawString(i,);
        }
         */
        if(background != null) {
            background.draw(g);
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

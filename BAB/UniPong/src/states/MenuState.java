package states;

import entities.AnimatedGIF;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends State {
    private AnimatedGIF background;
    private String[] options;
    private int currentOptionIndex;

    public MenuState() {
        super();
        options = new String[]{"Play","Look Cool","Exit"};
        currentOptionIndex = 0;
        init();
    }
    @Override
    public void init() {
        background.readGIF("/menubg.gif");
    }

    @Override
    public void update() {
        background.nextFrame();
    }

    @Override
    public void paint(Graphics2D g) {
        //TODO styling here
    }

    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_DOWN) {
            ++currentOptionIndex;
        }
        else if(keyCode == KeyEvent.VK_UP) {
            --currentOptionIndex;
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

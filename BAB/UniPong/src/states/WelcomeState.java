package states;

import entities.AnimatedGIF;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WelcomeState extends State {
    private boolean isFinished = false;
    private AnimatedGIF background;

    public void init() {
        background.readGIF("/menubg.gif");
    }

    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics2D g) {

    }

    @Override
    public void keyPressed(int keyCode) {

    }

    @Override
    public void keyReleased(int keyCode) {

    }
}

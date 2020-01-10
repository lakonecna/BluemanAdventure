package states;

import java.awt.*;

public class Controller {
    private State[] states;
    private State currentState;


    public Controller() {
        states = new State[10];
        states[0] = new MenuState();
        currentState = states[0];
    }

    public void init() {
        currentState.init();
    }

    public void update() {
        currentState.update();
    }

    public void paint(Graphics2D g) {
        currentState.paint(g);
    }

    public void keyPressed(int keyCode) {
        currentState.keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
        currentState.keyReleased(keyCode);
    }

    public void nextState() {

    }
}

package states;

import java.awt.*;

public class Controller {
    private State[] states;
    private int currentStateIndex;
    private final static int stateCount = 2;
    private boolean playing;

    public Controller() {
        states = new State[stateCount];
        states[0] = new MenuState();
        states[1] = new GameState();
        currentStateIndex = 0;
        playing = true;
    }

    private void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean getPlaying() {
        return playing;
    }

    public void init() {
        states[currentStateIndex].init();
    }

    public void update() {
        states[currentStateIndex].update();
    }

    public void paint(Graphics2D g) {
        states[currentStateIndex].paint(g);
    }

    public void keyPressed(int keyCode) {
        states[currentStateIndex].keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
        states[currentStateIndex].keyReleased(keyCode);
    }

    public void nextState() {
        ++currentStateIndex;
        if(currentStateIndex == stateCount) {
            playing = false;
        }
    }

    public void menuRequestsGame() {
        nextState();
    }

    public void menuRequestsExit() {
        //TODO exiting game
    }
}

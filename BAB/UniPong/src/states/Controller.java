package states;

import java.awt.*;

public class Controller {
    private State[] states;
    private int currentStateIndex;
    private final static int stateCount = 6;
    private boolean playing;

    public Controller() {
        states = new State[stateCount];
        states[0] = new WelcomeState(this);
        states[1] = new MenuState(this);
        //states[2] is initialized as needed
        states[3] = new GameOverState(this);
        states[4] = new LookingCoolState(this);
        states[5] = new ExitState(this);
        currentStateIndex = 0;
        playing = true;
    }

    public void init() {
        // will this be needed?
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
        states[2] = new GameState(this);
        nextState();
    }

    public void menuRequestsExit() {
        currentStateIndex = 5;
    }

    public void menuRequestslcs() { currentStateIndex = 4; }

    public void welcomeRequestsMenu() {
        nextState();
    }

    public void gameOverRequestsMenu() {
        currentStateIndex = 1;
    }

    public void exitRequestsExit() {
        playing = false;
    }

    public void gameRequestsGameOver() {
        nextState();
    }

    public void lcsRequestsMenu() { currentStateIndex = 1; }

    public boolean isRunning() {
        return playing;
    }
}

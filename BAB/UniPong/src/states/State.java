package states;

import java.awt.*;

public abstract class State {
    public abstract void init();
    public abstract void update();
    public abstract void paint(Graphics2D g);
    public abstract void keyPressed(int keyCode);
    public abstract void keyReleased(int keyCode);
}

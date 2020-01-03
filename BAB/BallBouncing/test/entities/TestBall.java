package entities;

import main.GamePanel;
import math.Vector2D;

import static org.junit.Assert.*;
//import org.junit.Test;

// test folder was marked as test sources root, right click, mark directory as, test sources root
// content dirs were automatically converted to packages
// class was added
// above import worked after a quick fix
public class TestBall {

    public void testConstructor() {
        Ball ball = new Ball(100,100,50);
        assertTrue(ball.getX() == 100);
        assertTrue(ball.getY() == 100);
    }

    public void testMoveBall() {
        Ball ball = new Ball(100,100,50);
        Vector2D displacement = new Vector2D(2,3);
        ball.setDisplacement(displacement);
        ball.update();
        assertTrue(ball.getX() == 102);
        assertTrue(ball.getY() == 103);
    }

    public void testMoveBallOffRightScreen() {
        Ball ball = new Ball(589,100,50);
        Vector2D displacement = new Vector2D(2,3);
        ball.setDisplacement(displacement);
        ball.update();
        assertTrue(ball.getX() == GamePanel.WIDTH - (2 * ball.getRadius()) - 1);
        assertTrue(ball.getY() == 103);
        assertTrue(ball.getDisplacement().getX() < 0);
    }

    public void testMoveBallOffLeftScreen() {
        Ball ball = new Ball(1,100,50);
        Vector2D displacement = new Vector2D(-2,3);
        ball.setDisplacement(displacement);
        ball.update();
        assertTrue(ball.getX() == 0);
        assertTrue(ball.getY() == 103);
        assertTrue(ball.getDisplacement().getX() > 0);
    }

    public void testMoveBallOffTopScreen() {
        Ball ball = new Ball(100,0,50);
        Vector2D displacement = new Vector2D(2,-3);
        ball.setDisplacement(displacement);
        ball.update();
        assertTrue(ball.getX() == 102);
        assertTrue(ball.getY() == 0);
        assertTrue(ball.getDisplacement().getY() > 0);
    }

    public void testMoveBallOffBottomScreen() {
        Ball ball = new Ball(100,378,50);
        Vector2D displacement = new Vector2D(2,3);
        ball.setDisplacement(displacement);
        ball.update();
        assertTrue(ball.getX() == 102);
        assertTrue(ball.getY() == GamePanel.HEIGHT - (2 * ball.getRadius()) - 1);
        assertTrue(ball.getDisplacement().getY() < 0);
    }
}

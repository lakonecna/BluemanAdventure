package math;

public class Vector2D {
    private int x;
    private int y;

    public Vector2D(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void vectorAddition(Vector2D otherVector) {
        x += otherVector.getX();
        y += otherVector.getY();
    }

    public void scalarMultiplication(int scalar) {
        x *= scalar;
        y *= scalar;
    }
}

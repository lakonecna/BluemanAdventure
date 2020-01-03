package math;

public class Vector2D {
    private int x;
    private int y;

    public Vector2D(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public int getX() { return x; }

    public int getY() { return y; }

    public void vectorAddition(Vector2D vector) {
        x += vector.getX();
        y += vector.getY();
    }

    public void scalarMultiplication(int scalar) {
        x *= scalar;
        y *= scalar;
    }
}

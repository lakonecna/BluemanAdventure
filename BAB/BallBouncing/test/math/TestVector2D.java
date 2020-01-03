package math;

//TODO Learn how to use a build tool with these
// at this point the directory topology may be too
// messed up to do this and time will probably be wasted
import static org.junit.Assert.*;
import org.junit.Test;

public class TestVector2D {

    public void testConstructor() {
        Vector2D displacement = new Vector2D(2,3);
        assertEquals(displacement.getX(),2);
        assertEquals(displacement.getY(),3);
    }

    public void testVectorAdd() {
        Vector2D displacement = new Vector2D(2, 3);
        Vector2D vector2 = new Vector2D(-1, -1);
        displacement.vectorAdd(vector2);
        assertEquals(displacement.getX(), 1);
        assertEquals(displacement.getY(),2);
    }

    public void testScalarMultiply() {
        Vector2D displacement = new Vector2D(2, 3);
        displacement.scalarMultiply(-1);
        assertEquals(displacement.getX(),-2);
        assertEquals(displacement.getY(),-3);
    }
}

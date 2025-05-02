
import geometria.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CollCircleTest {

    @Test
    void testCircleExample1() {
        // Circle (10 4 0 0 1) (10 4 1)
        Transform transform = new Transform(new Point(10, 4), 0, 0, 1);
        Collider baseCollider = new CollCircle(1.0, transform);
        CollCircle collCircle = new CollCircle(baseCollider);

        String expected = "(10,00,4,00) 1,00";
        assertEquals(expected, collCircle.toString());
    }

    @Test
    void testBulletExample3() {
        // bullet (1.5 2.5 4 0 1) (1.5 2.5 1)
        Transform transform = new Transform(new Point(1.5, 2.5), 4, 0, 1);
        Collider baseCollider = new CollCircle(1.0, transform);
        CollCircle collCircle = new CollCircle(baseCollider);

        String expected = "(1,50,2,50) 1,00";
        assertEquals(expected, collCircle.toString());
    }

    @Test
    void testCollCircleCopy() {
        Transform transform = new Transform(new Point(10, 4), 0, 0, 1);
        Collider baseCollider = new CollCircle(2.0, transform);
        CollCircle original = new CollCircle(baseCollider);
        CollCircle copy = new CollCircle(original);

        assertEquals(original.toString(), copy.toString());
        assertNotSame(original, copy);
    }
}
import geometria.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColliderTest {

    @Test
    void collCircleToStringReflectsTransformAndRadius() {
        Transform transform = new Transform(new Point(5, 5), 0, 0, 1);
        CollCircle collCircle = new CollCircle(2.0, transform);

        String expected = "(5,00,5,00) 2,00";
        assertEquals(expected, collCircle.toString());
    }

    @Test
    void collCircleCopyMaintainsTransformAndRadius() {
        Transform transform = new Transform(new Point(3, 3), 0, 0, 1);
        CollCircle original = new CollCircle(1.5, transform);
        CollCircle copy = new CollCircle(original);

        assertEquals(original.toString(), copy.toString());
        assertNotSame(original, copy);
    }

    @Test
    void collCircleHandlesNullTransformGracefully() {
        assertThrows(NullPointerException.class, () -> new CollCircle(1.0, null));
    }

    @Test
    void collCircleHandlesZeroRadius() {
        Transform transform = new Transform(new Point(0, 0), 0, 0, 1);
        CollCircle collCircle = new CollCircle(0.0, transform);

        String expected = "(0,00,0,00) 0,00";
        assertEquals(expected, collCircle.toString());
    }

    @Test
    void collCircleUpdatesTransformCorrectly() {
        Transform initialTransform = new Transform(new Point(2, 2), 0, 0, 1);
        CollCircle collCircle = new CollCircle(1.0, initialTransform);

        Transform newTransform = new Transform(new Point(4, 4), 0, 0, 2);
        collCircle.setTransform(newTransform);
        collCircle.onUpdate();

        String expected = "(4,00,4,00) 2,00";
        assertEquals(expected, collCircle.toString());
    }
}
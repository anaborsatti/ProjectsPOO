import geometria.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CollPolyTest {

    @Test
    void testSquareExample() {
        // Square (2 2 0 0 1) (1 1 1 3 3 3 3 1)
        double[] vertices = {1, 1, 1, 3, 3, 3, 3, 1};
        Transform transform = new Transform(new Point(2, 2), 0, 0, 1);
        Collider baseCollider = new CollPoly(vertices, transform);
        CollPoly collPoly = new CollPoly(baseCollider);

        String expected = "(1,00,1,00) (1,00,3,00) (3,00,3,00) (3,00,1,00)";
        assertEquals(expected, collPoly.toString());
    }

    @Test
    void testRectExample() {
        // Rect (5 5 0 0 1) (4 3 4 7 6 7 6 3)
        double[] vertices = {4, 3, 4, 7, 6, 7, 6, 3};
        Transform transform = new Transform(new Point(5, 5), 0, 0, 1);
        Collider baseCollider = new CollPoly(vertices, transform);
        CollPoly collPoly = new CollPoly(baseCollider);

        String expected = "(4,00,3,00) (4,00,7,00) (6,00,7,00) (6,00,3,00)";
        assertEquals(expected, collPoly.toString());
    }
}
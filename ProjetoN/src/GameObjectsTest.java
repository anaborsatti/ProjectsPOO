import geometria.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GAmeObjectsTest {

    @Test
    void newGameObject1() {
        String name = "Alien01";
        Transform transform = new Transform(new Point(1.0, 2.0), 1, 0.0, 1.0);
        Collider collider = new Collider(3.0, transform);

        GameObjects gameObject = new GameObjects(name, transform, collider);

        String expectedName = "Alien01";
        String expectedTransform = "(1.00,2.00) 1 0.00 1.00";
        String expectedCollider = "(1.00,2.00) 3.00";

        assertEquals(expectedName, gameObject.name());
        assertEquals(expectedTransform, gameObject.transform().toString());
        assertEquals(expectedCollider, gameObject.collider().toString());
    }

    @Test
    void newGameObject2() {
        String name = "Alien02";
        Transform transform = new Transform(new Point(3.0, 7.0), 2, 45.6, 2.0);
        Collider collider = new Collider(3.0, transform);

        GameObjects gameObject = new GameObjects(name, transform, collider);

        String expectedName = "Alien02";
        String expectedTransform = "(3.00,7.00) 2 45.60 2.00";
        String expectedCollider = "(3.00,7.00) 6.00";

        assertEquals(expectedName, gameObject.name());
        assertEquals(expectedTransform, gameObject.transform().toString());
        assertEquals(expectedCollider, gameObject.collider().toString());
    }

    @Test
    void newGameObject3() {
        String name = "Alien03";
        Transform transform = new Transform(new Point(5.0, 9.0), 0, 90, 2);
        double[] valores = {2, 2, 2, 6, 4, 6, 4, 2};
        Collider collider = new Collider(valores, transform);

        GameObjects gameObject = new GameObjects(name, transform, collider);

        String expectedName = "Alien03";
        String expectedTransform = "(5.00,9.00) 0 90.00 2.00";
        String expectedCollider = "(9.00,7.00) (1.00,7.00) (1.00,11.00) (9.00,11.00)";

        assertEquals(expectedName, gameObject.name());
        assertEquals(expectedTransform, gameObject.transform().toString());
        assertEquals(expectedCollider, gameObject.collider().toString());
    }

    @Test
    void newGameObject4() {
        String name = "Alien01";
        Transform transform = new Transform(new Point(1.0, 2.0), 1, 0, 1);
        Collider collider = new Collider(3, transform);

        GameObjects gameObject = new GameObjects(name, transform, collider);

        String expectedName = "Alien01";
        String expectedTransform = "(2.00,3.00) 1 90.00 1.00";
        String expectedCollider = "(2.00,3.00) 3.00";

        gameObject.move(new Point(1, 1), 0);
        gameObject.rotate(90);

        assertEquals(expectedName, gameObject.name());
        assertEquals(expectedTransform, gameObject.transform().toString());
        assertEquals(expectedCollider, gameObject.collider().toString());
    }

    @Test
    void newGameObject5() {
        String name = "Alien02";
        Transform transform = new Transform(new Point(0.0, 0.0), 2, 0, 1);
        Collider collider = new Collider(3, transform);

        GameObjects gameObject = new GameObjects(name, transform, collider);

        String expectedName = "Alien02";
        String expectedTransform = "(3.00,7.00) 4 0.00 2.00";
        String expectedCollider = "(3.00,7.00) 6.00";

        gameObject.move(new Point(3, 7), 2);
        gameObject.scale(1);

        assertEquals(expectedName, gameObject.name());
        assertEquals(expectedTransform, gameObject.transform().toString());
        assertEquals(expectedCollider, gameObject.collider().toString());
    }

    @Test
    void newGameObject6() {
        String name = "Monster";
        Transform transform = new Transform(new Point(3.0, 6.0), 0, 90, 1);
        double[] valores = {0, 0, 0, 2, 2, 2, 2, 0};
        Collider collider = new Collider(valores, transform);

        GameObjects gameObject = new GameObjects(name, transform, collider);

        String expectedName = "Monster";
        String expectedTransform = "(4.00,7.00) 0 0.00 1.00";
        String expectedCollider = "(3.00,6.00) (3.00,8.00) (5.00,8.00) (5.00,6.00)";

        gameObject.move(new Point(1, 1), 0);
        gameObject.rotate(-90);

        assertEquals(expectedName, gameObject.name());
        assertEquals(expectedTransform, gameObject.transform().toString());
        assertEquals(expectedCollider, gameObject.collider().toString());
    }
}
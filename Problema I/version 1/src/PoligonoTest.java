import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PoligonoTest {

    @Test
    void testToString() {
        Poligono poligono = new Poligono("4 5 5 8 6 8 7 5 7");
        assertEquals("Poligono de 4 vertices: [(5,5), (8,6), (8,7), (5,7)]", poligono.toString());
    }

    @Test
    void testToString2() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Poligono("5 5 5 6 6 7 7 5 7 2 1"));
        assertEquals("Poligono:vi", exception.getMessage());
    }

    @Test
    void testToString3() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Poligono("4 5 5 8 6 8 7 5 7"));
        assertEquals("Poligono:vi", exception.getMessage());
    }

}

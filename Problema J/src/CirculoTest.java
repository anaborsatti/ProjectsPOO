import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CirculoTest {

    @Test
    void testToString() {
        Circulo circulo = new Circulo("2 2 1");
        assertEquals("Circulo: (2,2) 1", circulo.toString());
    }
}

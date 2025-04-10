import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TrianguloTest {

    @Test
    void testToString() {
        Triangulo triangulo = new Triangulo("7 1 9 1 9 3");
        assertEquals("Triangulo: [(7,1), (9,1), (9,3)]", triangulo.toString());
    }
}

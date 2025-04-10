import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RetanguloTest {

    @Test
    public void testRetanguloInvalidoCaso1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Retangulo("3 0 4 2 3 4 2 2");
        });

        assertEquals("Retangulo:vi", exception.getMessage());
    }

    @Test
    public void testPontoInvalidoCaso2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Retangulo("-1 0 4 2 3 4 2 2");
        });

        assertEquals("Ponto:vi", exception.getMessage());
    }

    @Test
    public void testRetanguloValidoCaso3() {
        Retangulo retangulo = new Retangulo("4 1 6 1 6 2 4 2");
        assertEquals("Retangulo: [(4,1), (6,1), (6,2), (4,2)]", retangulo.toString());
    }
}

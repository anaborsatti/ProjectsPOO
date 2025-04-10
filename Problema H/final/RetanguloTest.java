
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RetanguloTest {

    @Test
    public void testRetanguloInvalidoCaso1() {
        List<Ponto> pontos = List.of(
                new Ponto(3, 0),
                new Ponto(4, 2),
                new Ponto(3, 4),
                new Ponto(2, 2)
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Retangulo(pontos);
        });

        assertEquals("Retangulo:vi", exception.getMessage());
    }

    @Test
    public void testPontoInvalidoCaso2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            List<Ponto> pontos = List.of(
                    new Ponto(-1, 0),
                    new Ponto(4, 2),
                    new Ponto(3, 4),
                    new Ponto(2, 2)
            );
            new Retangulo(pontos);
        });

        assertEquals("Ponto:vi", exception.getMessage());
    }

    @Test
    public void testRetanguloValidoCaso3() {
        List<Ponto> pontos = List.of(
                new Ponto(4, 1),
                new Ponto(6, 1),
                new Ponto(6, 2),
                new Ponto(4, 2)
        );

        Retangulo retangulo = new Retangulo(pontos);
        assertEquals("Retangulo: [(4,1), (6,1), (6,2), (4,2)]", retangulo.toString());
    }
}

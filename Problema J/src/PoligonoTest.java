import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

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
    void testTranslacao1() {
        Poligono poligono = new Poligono("4 1 2 5 6 8 7 12 14");

        List<Ponto> pontosTransladados = poligono.translacao(-1, 3);

        StringBuilder sb = new StringBuilder();
        sb.append("4 ");
        for (Ponto p : pontosTransladados) {
            sb.append(p.getX()).append(" ").append(p.getY()).append(" ");
        }

        Poligono transladado = new Poligono(sb.toString().trim());

        assertEquals("Poligono de 4 vertices: [(0,5), (4,9), (7,10), (11,17)]", transladado.toString());
    }





}
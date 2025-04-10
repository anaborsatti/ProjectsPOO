import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;

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

    @Test
    void testPontoDentroDoPoligono() {
        Poligono poligono = new Poligono("4 0 0 4 0 4 4 0 4");
        Ponto p = new Ponto(2, 2); // Dentro do polígono
        assertTrue(poligono.contemPonto(p));
    }

    @Test
    void testPontoForaDoPoligono() {
        Poligono poligono = new Poligono("4 0 0 4 0 4 4 0 4");
        Ponto p = new Ponto(5, 5); // Fora do polígono
        assertFalse(poligono.contemPonto(p));
    }

    @Test
    void testPontoNaBordaDoPoligono() {
        Poligono poligono = new Poligono("4 0 0 4 0 4 4 0 4");
        Ponto p = new Ponto(4, 2); // Na borda
        assertTrue(poligono.contemPonto(p));
    }

    @Test
    void testPontoNoVerticeDoPoligono() {
        Poligono poligono = new Poligono("4 0 0 4 0 4 4 0 4");
        Ponto p = new Ponto(0, 0); // No vértice
        assertTrue(poligono.contemPonto(p));
    }


}

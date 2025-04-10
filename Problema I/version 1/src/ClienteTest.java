import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ClienteTest {
    @Test
    void testEntrada1() {
        Poligono poligono = new Poligono("4 5 5 8 6 8 7 5 7");
        assertEquals("Poligono de 4 vertices: [(5,5), (8,6), (8,7), (5,7)]", poligono.toString());
    }

    @Test
    void testEntrada2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Retangulo("3 0 4 2 3 4 2 2");
        });
        assertEquals("Retangulo:vi", thrown.getMessage());
    }

    @Test
    void testEntrada3() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Poligono("5 5 5 6 6 7 7 5 7 2 1");
        });
        assertEquals("Poligono:vi", thrown.getMessage());
    }

    @Test
    void testCirculo() {
        Circulo circulo = new Circulo("2 2 1");
        assertEquals("Circulo: (2,2) 1", circulo.toString());
    }

    @Test
    void testTriangulo() {
        Triangulo triangulo = new Triangulo("7 1 9 1 9 3");
        assertEquals("Triangulo: [(7,1), (9,1), (9,3)]", triangulo.toString());
    }
}

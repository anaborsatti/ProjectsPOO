import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CirculoTest {

    @Test
    void testToString() {
        Circulo circulo = new Circulo("2 2 1");
        assertEquals("Circulo: (2,2) 1", circulo.toString());
    }

    @Test
    void testColisao1() {
        Circulo c1 = new Circulo("3 3 2");
        Circulo c2 = new Circulo("5 5 2");

        assertTrue(c1.colisao(c2));
    }

    @Test
    void testeColisao2() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Circulo("1 1 2"));

        assertEquals("Circulo:vi", exception.getMessage());
    }

    @Test
    void testeColisao3() {
        Circulo circulo = new Circulo("3 3 2");

        assertEquals("Circulo: (3,3) 2", circulo.toString());
    }

    @Test
    void testColisaoCirculoComTriangulo() {
        Circulo circulo = new Circulo("2 2 1");
        Poligono triangulo = new Poligono("3 7 1 9 1 9 3");
        assertFalse(circulo.colisao(triangulo));
    }

    @Test
    void testColisaoCirculoComPoligono() {
        Circulo circulo = new Circulo("2 2 1");
        Poligono poligono = new Poligono("4 5 5 8 6 8 7 5 7");
        assertFalse(circulo.colisao(poligono));
    }
    @Test
    void testColisaoPoligonoComCirculo() {

        Poligono poligono = new Poligono("4 10 10 10 50 50 50 50 10");
        Circulo circulo = new Circulo("30 30 10");
        assertTrue(circulo.colisao(poligono), "O círculo e o polígono devem colidir");
    }

    @Test
    void testColisaoPoligonoComCirculoSemColisao() {
        Retangulo retangulo = new Retangulo("20 20 20 40 40 40 40 20");
        Circulo circulo = new Circulo("30 30 20");
        assertTrue(circulo.colisao(retangulo), "eles colidem");
    }


}

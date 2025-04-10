import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    void testColisaoPoligonoComCirculo() throws Exception {
        // Cria os objetos Polígono (Retângulo) e Circulo
        Retangulo retangulo = new Retangulo("20 20 20 40 40 40 40 20");
        Circulo circulo = new Circulo("30 30 20");

        // Adiciona as figuras à lista
        List<FiguraGeometrica> figuras = new ArrayList<>();
        figuras.add(retangulo);
        figuras.add(circulo);

        // Chama o método verificarColisoes e obtém o retorno
        String result = Cliente.verificarColisoes(figuras);

        // Verifica se o resultado está correto
        assertEquals("Colisao em 0", result);
    }

    @Test
    void testeColisaoOrdem(){
        Poligono poligono = new Poligono("4 5 5 8 6 8 7 5 7");
        Triangulo triangulo = new Triangulo("7 1 9 1 9 3");
        Circulo circulo1 = new Circulo("2 2 1");
        Circulo circulo2 = new Circulo("5 8 1.05");

        List<FiguraGeometrica> figuras = new ArrayList<>();
        figuras.add(poligono);
        figuras.add(triangulo);
        figuras.add(circulo1);
        figuras.add(circulo2);

        String result = Cliente.verificarColisoes(figuras);
        System.out.println(result);  // Espera-se: "Colisao em 0"

    }
    @Test
    void testColisaoPoligonoComCirculo3() throws Exception {
        // Cria os objetos Polígono (Retângulo) e Circulo
        Retangulo retangulo = new Retangulo("10 10 10 50 50 50 50 10");
        Circulo circulo = new Circulo("30 30 10");

        // Adiciona as figuras à lista
        List<FiguraGeometrica> figuras = new ArrayList<>();
        figuras.add(retangulo);
        figuras.add(circulo);

        // Chama o método verificarColisoes e obtém o retorno
        String result = Cliente.verificarColisoes(figuras);

        // Verifica se o resultado está correto (esperado "Colisao em 0" pois o retângulo está na posição 0)
        assertEquals("Colisao em 0", result);
    }



}

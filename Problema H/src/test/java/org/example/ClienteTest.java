package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class ClienteTest {

    @Test
    void testExemplo1() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Retangulo(Arrays.asList(
                    new Ponto(-1, 0), new Ponto(4, 2),
                    new Ponto(3, 4), new Ponto(2, 2)
            ));
        });
        assertEquals("Ponto:vi", thrown.getMessage());
    }

    @Test
    void testExemplo2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Retangulo(Arrays.asList(
                    new Ponto(3, 0), new Ponto(4, 2),
                    new Ponto(3, 4), new Ponto(2, 2)
            ));
        });
        assertEquals("Retangulo:vi", thrown.getMessage());
    }

    @Test
    void testExemplo3() {
        Retangulo retangulo = new Retangulo(Arrays.asList(
                new Ponto(4, 1), new Ponto(6, 1),
                new Ponto(6, 2), new Ponto(4, 2)
        ));
        Segmento segmento = new Segmento(new Ponto(3, 1), new Ponto(4, 3));

        assertEquals(false, retangulo.intersection(segmento));
    }

    @Test
    void testExemplo4() {
        Retangulo retangulo = new Retangulo(Arrays.asList(
                new Ponto(4, 1), new Ponto(6, 1),
                new Ponto(6, 2), new Ponto(4, 2)
        ));
        Segmento segmento = new Segmento(new Ponto(4, 0), new Ponto(5, 3));

        assertEquals(true, retangulo.intersection(segmento));
    }

    @Test
    void testExemploT08() {
        Retangulo retangulo = new Retangulo(Arrays.asList(
                new Ponto(3, 2), new Ponto(4, 1),
                new Ponto(6, 3), new Ponto(5, 4)
        ));
        Segmento segmento = new Segmento(new Ponto(1, 3), new Ponto(4, 3));

        assertEquals(false, retangulo.intersection(segmento)); // Espera-se que o segmento não intersecte
    }

    @Test
    void testExemploT09() {
        Retangulo retangulo = new Retangulo(Arrays.asList(
                new Ponto(3, 2), new Ponto(4, 1),
                new Ponto(6, 3), new Ponto(5, 4)
        ));
        Segmento segmento = new Segmento(new Ponto(1, 3), new Ponto(4, 3));

        assertEquals(false, retangulo.intersection(segmento)); // Espera-se que o segmento não intersecte
    }

    @Test
    void testExemploT10() {
        Retangulo retangulo = new Retangulo(Arrays.asList(
                new Ponto(0, 0), new Ponto(5, 0),
                new Ponto(5, 5), new Ponto(0, 5)
        ));
        Segmento segmento = new Segmento(new Ponto(5, 2), new Ponto(5, 10));

        assertEquals(false, retangulo.intersection(segmento)); // Espera-se que o segmento intersecte
}}

package geometria;

import java.util.List;

/**
 * Classe que representa um triângulo definido por três pontos.
 * Garante que os pontos formam um triângulo válido e não são colineares.
 */
public class Triangulo extends Poligono {

    /**
     * Construtor da classe Triangulo.
     * Garante que os pontos formam um triângulo válido.
     *
     * @param valores String contendo as coordenadas dos três pontos do triângulo.
     */
    public Triangulo(double[] valores) {
        super(valores);
        check(vertices);
    }

    /**
     * Verifica se a lista de pontos define um triângulo válido.
     * O triângulo deve ter exatamente três pontos e não pode ser colinear.
     *
     * @param pontos Lista de três pontos que devem formar um triângulo.
     */
    private void check(List<Point> pontos) {
        if (pontos.size() != 3) {
            System.out.println("Triangulo:vi");
            System.exit(0);
        }

        // Verifica se os três pontos são colineares
        if (Segmento.colinear(pontos.get(0), pontos.get(1), pontos.get(2))) {
            System.out.println("Triangulo:vi");
            System.exit(0);
        }
    }

    /**
     * Representação textual do triângulo no formato [(x1,y1), (x2,y2), (x3,y3)].
     *
     * @return String representando os pontos do triângulo.
     */
    @Override
    public String toString() {
        return "Triangulo: " +vertices.toString();
    }
}

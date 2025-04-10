package geometria;

import java.util.List;

/**
 * Classe que representa um retângulo definido por quatro pontos.
 * Garante que os pontos formam um retângulo válido com ângulos retos.
 *
 * @inv O retângulo deve ter exatamente quatro pontos e formar um ângulo reto em cada vértice.
 */
public class Retangulo extends Poligono {


    /**
     * Verifica se a lista de pontos define um retângulo válido.
     * O programa encerra caso as condições não sejam atendidas.
     *
     * @param pontos Lista de quatro pontos que devem formar um retângulo.
     */
    private void check(List<Point> pontos) {
        if (pontos.size() != 4) {
            System.out.println("Retangulo:vi");
            System.exit(0);
        }

        // Calcula vetores entre pontos consecutivos
        double[] v1 = {pontos.get(1).getX() - pontos.get(0).getX(), pontos.get(1).getY() - pontos.get(0).getY()};
        double[] v2 = {pontos.get(2).getX() - pontos.get(1).getX(), pontos.get(2).getY() - pontos.get(1).getY()};
        double[] v3 = {pontos.get(3).getX() - pontos.get(2).getX(), pontos.get(3).getY() - pontos.get(2).getY()};
        double[] v4 = {pontos.get(0).getX() - pontos.get(3).getX(), pontos.get(0).getY() - pontos.get(3).getY()};

        // Verifica se os ângulos entre os vetores são de 90° (produto escalar deve ser zero)
        if (!iguais(v1[0] * v2[0] + v1[1] * v2[1], 0) ||
                !iguais(v2[0] * v3[0] + v2[1] * v3[1], 0) ||
                !iguais(v3[0] * v4[0] + v3[1] * v4[1], 0) ||
                !iguais(v4[0] * v1[0] + v4[1] * v1[1], 0)) {
            System.out.println("Retangulo:vi");
            System.exit(0);
        }
    }

    /**
     * Construtor da classe Retangulo.
     * Garante que os pontos formam um retângulo válido.
     *
     * @param valores Lista de quatro pontos que definem o retângulo.
     */
    public Retangulo(double[] valores) {
        super(valores);

        check(vertices);
    }



    /**
     * Representação textual do retângulo no formato [(x1,y1), (x2,y2), (x3,y3), (x4,y4)].
     *
     * @return String representando os pontos do retângulo.
     */
    @Override
    public String toString() {
        return "Retangulo: " + vertices.toString();
    }

    /**
     * Verifica se dois valores de ponto flutuante são aproximadamente iguais dentro de uma margem de erro (EPS).
     *
     * @param d Primeiro valor a ser comparado.
     * @param g Segundo valor a ser comparado.
     * @return true se os valores forem aproximadamente iguais, false caso contrário.
     */
    private boolean iguais(double d, double g) {
        return Math.abs(d - g) < EPS;
    }
}

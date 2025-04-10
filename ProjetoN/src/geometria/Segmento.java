package geometria;


/**
 * Classe que representa um segmento de reta definido por dois pontos.
 * Garante que os pontos não sejam coincidentes.
 *
 * @inv O segmento deve ter dois pontos distintos, ou seja, os pontos A e B não podem ser iguais.
 */
public class Segmento {
    private Point a;
    private Point b;
    private static final double EPS = 1e-9;

    /**
     * Verifica se os dois pontos do segmento são coincidentes, se forem, encerra o programa.
     */
    private void check(Point a, Point b) {
        if (a.equals(b)) {
            System.out.println("Segmento:vi");
            System.exit(0);
        }
    }

    /**
     * Construtor da classe Segmento.
     */
    public Segmento(Point a, Point b) {
        check(a, b);
        this.a = a;
        this.b = b;
    }

    /**
     * Retorna o primeiro ponto do segmento.
     *
     * @return O ponto A do segmento.
     */
    public Point getA() {
        return a;
    }

    /**
     * Retorna o segundo ponto do segmento.
     *
     * @return O ponto B do segmento.
     */
    public Point getB() {
        return b;
    }

    /**
     * Verifica se dois segmentos se interceptam.
     * Realiza o cálculo dos parâmetros de interseção usando determinante e parâmetros t e u.
     *
     * @param that O outro segmento de reta a ser comparado.
     * @return true se os segmentos se intersectam, caso contrário false.
     */
    public boolean intersecta(Segmento that) {
        double d = determinante(that);

        if (d == 0) {

            return (onSegment(that.a, this.a, this.b) ||
                    onSegment(that.b, this.a, this.b) ||
                    onSegment(this.a, that.a, that.b) ||
                    onSegment(this.b, that.a, that.b));
        }

        double t = intersecaoEmA(that, d);
        double u = intersecaoEmB(that, d);

        return (t > 0.0 && t < 1.0) && (u > 0.0 && u < 1.0);
    }

    /**
     * Calcula o determinante usado para verificar a interseção dos segmentos.
     *
     * @param that O outro segmento a ser comparado.
     * @return O valor do determinante.
     */
    private double determinante(Segmento that) {
        double x1 = a.getX(), y1 = a.getY();
        double x2 = b.getX(), y2 = b.getY();
        double x3 = that.a.getX(), y3 = that.a.getY();
        double x4 = that.b.getX(), y4 = that.b.getY();

        return (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
    }

    /**
     * Calcula a intersecao no Segmento A
     *
     * @param that O outro segmento.
     * @param d O determinante calculado.
     * @return a posição relativa do ponto de interseção no segmento
     */
    private double intersecaoEmA(Segmento that, double d) {
        double x1 = a.getX(), y1 = a.getY();
        double x3 = that.a.getX(), y3 = that.a.getY();
        double x4 = that.b.getX(), y4 = that.b.getY();

        return ((x3 - x1) * (y4 - y3) - (y3 - y1) * (x4 - x3)) / d;
    }

    /**
     * Calcula a intersecao no Segmento B
     *
     * @param that O outro segmento.
     * @param d O determinante calculado.
     * @return a posição relativa do ponto de interseção no segmento
     */
    private double intersecaoEmB(Segmento that, double d) {
        double x1 = a.getX(), y1 = a.getY();
        double x2 = b.getX(), y2 = b.getY();
        double x3 = that.a.getX(), y3 = that.a.getY();

        return ((x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1)) / d;
    }

    /**
     * Verifica se um ponto c está dentro de um segmento
     *
     * @param c O ponto a ser verificado.
     * @param a O ponto inicial do segmento.
     * @param b O ponto final do segmento.
     * @return true se o ponto c estiver dentro do segmento ab, caso contrário false.
     */
    private boolean onSegment(Point c, Point a, Point b) {
        return (c.getX() > Math.min(a.getX(), b.getX()) && c.getX() < Math.max(a.getX(), b.getX()) &&
                c.getY() > Math.min(a.getY(), b.getY()) && c.getY() < Math.max(a.getY(), b.getY()));
    }

    /**
     * Verifica se três pontos são colineares, utilizando o determinante.
     *
     * @param p1 O primeiro ponto.
     * @param p2 O segundo ponto.
     * @param p3 O terceiro ponto.
     * @return true se os pontos são colineares, caso contrário false.
     */
    public static boolean colinear(Point p1, Point p2, Point p3) {
        // Verifica a colinearidade utilizando o determinante
        Segmento segmento1 = new Segmento(p1, p2);
        Segmento segmento2 = new Segmento(p2, p3);
        double determinante = segmento1.determinante(segmento2); // Usando o determinante
        return Math.abs(determinante) < EPS;
    }
}

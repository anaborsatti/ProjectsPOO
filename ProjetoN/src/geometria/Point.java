package geometria;

/**
 * Classe que representa um ponto no plano utilizando coordenadas polares.
 * Garante que o ponto esteja no primeiro quadrante e que o raio seja não negativo.
 *
 * @inv O ponto deve estar no primeiro quadrante e ter um raio maior ou igual a zero.
 */
public class Point {
    private double x; // Raio (distância até a origem)
    private double y; // Ângulo em graus (0° a 90°)
    private static final double EPSILON = 1e-9;

    /**
     * Construtor padrão que inicializa o ponto na origem (0,0).
     */
    public Point() {
        x = 0;
        y = 0;
    }

    /**
     * Verifica se o ponto está no primeiro quadrante.
     * O raio deve ser não negativo, e o ângulo deve estar no intervalo de 0° a 90°.
     *
     * @param r O raio do ponto.
     * @param a O ângulo do ponto em graus.
     */
    private void check(double r, double a) {
        if (!(Point.doubleEquals(r, 0) || r > 0) || !(Point.doubleEquals(a, 0) || (a > 0 && a < 90) || Point.doubleEquals(a, 90))) {
            throw new IllegalArgumentException("Ponto:vi");
        }
    }


//    /**
//     * Construtor que inicializa o ponto com coordenadas cartesianas (x, y).
//     * As coordenadas são convertidas para coordenadas polares (r, a).
//     *
//     * @param x Coordenada x do ponto.
//     * @param y Coordenada y do ponto.
//     */
//    public Point(int x, int y) {
//        this.r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)); // Cálculo do raio
//        this.a = Math.toDegrees(Math.atan2(y, x)); // Cálculo do ângulo em graus
//
//        //check(r, a);
//    }

    /**
     * Construtor que inicializa o ponto diretamente com coordenadas polares (r, a).
     *
     * @param r Raio do ponto (distância da origem).
     * @param a Ângulo do ponto em graus (entre 0° e 90°).
     */
    public Point(double r, double a) {
        //check(r, a);
        this.x = r;
        this.y = a;
    }

    public Point(Point p)
    {
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * Calcula a distância entre este ponto e outro ponto fornecido.
     *
     * @param that Outro ponto a ser comparado.
     * @return A distância euclidiana entre os dois pontos.
     */
    public double dist(Point that) {
        double dx = this.getX() - that.getX();
        double dy = this.getY() - that.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Retorna a coordenada x do ponto convertida de coordenadas polares para cartesianas.
     *
     * @return O valor da coordenada x.
     */
    public double getX() {
        return x;
    }

    /**
     * Retorna a coordenada y do ponto convertida de coordenadas polares para cartesianas.
     *
     * @return O valor da coordenada y.
     */
    public double getY() {
        return y;
    }

    /**
     * Compara este ponto com outro para verificar se são iguais.
     *
     * @param other O outro ponto a ser comparado.
     * @return true se os pontos forem idênticos, false caso contrário.
     */
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        Point that = (Point) other;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f)", getX(), getY());
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) * 31 + Double.hashCode(y);
    }

    /**
     * Método global para comparar dois números do tipo double com precisão de 1e-9.
     *
     * @param d1 Primeiro número.
     * @param d2 Segundo número.
     * @return true se os valores forem considerados iguais dentro da margem de erro, false caso contrário.
     */
    public static boolean doubleEquals(double d1, double d2) {
        return Math.abs(d1 - d2) < EPSILON;
    }
}

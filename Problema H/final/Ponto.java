
import static java.lang.Math.*;

/**
 * Classe que representa um ponto no plano cartesiano em coordenadas polares.
 * Realiza operações como calcular distância, verificar ângulo reto e converter entre coordenadas.
 *
 * @author Ana Borsatti
 * @version 1.0, 09 Mar 2025
 * @inv A classe garante que o ponto está sempre no primeiro quadrante, com r >= 0 e 0 <= theta <= 90.
 */
class Ponto {
    private double r;  // Raio (distância do ponto à origem)
    private double theta;  // Ângulo em graus (ângulo formado com o eixo X)

    /**
     * Construtor que cria um ponto a partir de coordenadas polares.
     *
     * @param r O raio do ponto.
     * @param theta O ângulo do ponto, em graus.
     * @throws IllegalArgumentException Se o ponto não satisfizer a invariante da classe.
     */
    public Ponto(double r, double theta) {
        this.r = r;
        this.theta = theta;
        if (!invariant()) {
            throw new IllegalArgumentException("Ponto:vi");
        }
    }

    /**
     * Construtor que cria um ponto a partir de coordenadas cartesianas.
     * Converte as coordenadas cartesianas para polares.
     *
     * @param x A coordenada X do ponto.
     * @param y A coordenada Y do ponto.
     * @throws IllegalArgumentException Se o ponto não satisfizer a invariante da classe.
     */
    public Ponto(int x, int y) {
        this.r = Math.sqrt(x * x + y * y);
        this.theta = Math.toDegrees(Math.atan2(y, x));
        if (!invariant()) {
            throw new IllegalArgumentException("Ponto:vi");
        }
    }

    /**
     * Verifica se o ponto está no primeiro quadrante, ou seja, se o raio é positivo
     * e o ângulo está entre 0 e 90 graus.
     *
     * @return true se o ponto estiver no primeiro quadrante, caso contrário false.
     */
    private boolean invariant() {
        return (r >= 0 && theta >= 0 && theta <= 90);
    }

    /**
     * Calcula a distância entre o ponto atual e outro ponto em coordenadas polares.
     * A fórmula usada é a distância entre dois pontos no plano polar.
     *
     * @param that O outro ponto a ser comparado.
     * @return A distância entre os dois pontos.
     */
    public double dist(Ponto that) {
        return sqrt(pow(this.r, 2) + pow(that.r, 2) - 2 * this.r * that.r * cos(toRadians(that.theta) - (toRadians(this.theta))));
    }

    /**
     * Retorna o valor do raio (distância à origem).
     *
     * @return O raio do ponto.
     */
    public double getR() {
        return r;
    }

    /**
     * Retorna o valor do ângulo do ponto em graus.
     *
     * @return O ângulo em graus.
     */
    public double getTheta() {
        return theta;
    }

    /**
     * Retorna a coordenada X do ponto, convertendo de coordenadas polares para cartesianas.
     *
     * @return A coordenada X do ponto.
     */
    public int getX() {
        return (int) Math.round(r * Math.cos(Math.toRadians(theta)));
    }

    /**
     * Retorna a coordenada Y do ponto, convertendo de coordenadas polares para cartesianas.
     *
     * @return A coordenada Y do ponto.
     */
    public int getY() {
        return (int) Math.round(r * Math.sin(Math.toRadians(theta)));
    }

    /**
     * Verifica se o ponto atual e dois outros pontos formam um ângulo reto entre si,
     * usando o produto escalar.
     *
     * @param b O segundo ponto.
     * @param c O terceiro ponto.
     * @return true se os três pontos formarem um ângulo reto, caso contrário false.
     */
    public boolean formaAnguloReto(Ponto b, Ponto c) {
        int abX = b.getX() - this.getX();
        int abY = b.getY() - this.getY();
        int bcX = c.getX() - b.getX();
        int bcY = c.getY() - b.getY();

        int produtoEscalar = (abX * bcX) + (abY * bcY);

        return produtoEscalar == 0;
    }

    /**
     * Compara se dois pontos em coordenadas polares são iguais, ou seja, se têm
     * o mesmo raio e o mesmo ângulo.
     *
     * @param that O ponto a ser comparado.
     * @return true se os pontos forem iguais, caso contrário false.
     */
    public boolean equals(Ponto that) {
        return this.r == that.r && this.theta == that.theta;
    }
}

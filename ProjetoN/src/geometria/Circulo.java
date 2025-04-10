package geometria;


/**
 * Classe que representa um círculo definido por um centro e um raio.
 *
 * @inv O círculo deve estar completamente dentro do primeiro quadrante e o raio maior que zero.
 */
public class Circulo extends FiguraGeometrica {
    private Point centro;
    private double raio;

    /**
     * Verifica se o círculo está dentro dos limites válidos.
     * Se o círculo tiver parte fora do primeiro quadrante ou o raio for menor ou igual a zero, o programa encerra.
     *
     * @param ponto O ponto que representa o centro do círculo.
     * @param raio  O raio do círculo.
     */
    private void check(Point ponto, double raio) {
        if (raio < 0) {
            System.out.println("Circulo:vi");
            System.exit(0);
        }
    }

    /**
     * Construtor que recebe uma string com os argumentos do círculo.
     * Formato esperado: "x y raio", onde (x, y) é o centro e raio é o tamanho do círculo.
     *
     * @param valores array contendo os parâmetros do círculo.
     */
    public Circulo(double[] valores) {
        super(valores);
        double x = valores[0];
        double y = valores[1];
        double r = valores[2];

        check(new Point((int) x, (int) y), r);
        this.centro = new Point((int)x, (int)y);
        this.raio = r;
    }

    /**
     * Calcula e retorna o perímetro do círculo.
     *
     * @return O perímetro do círculo.
     */
    public double perimetro() {
        return Math.PI * (2 * raio);
    }

    /**
     * Retorna o centro do círculo.
     *
     * @return O ponto que representa o centro do círculo.
     */
    public Point getCentro() {
        return centro;
    }


    /**
     * Verifica se um segmento intersecta o círculo.
     * Um segmento intersecta um círculo se a projeção ortogonal do centro do círculo sobre o segmento estiver dentro
     * do segmento e a distância desse ponto projetado ao centro for menor ou igual ao raio.
     *
     * @param s O segmento a ser verificado.
     * @return true se houver interseção, false caso contrário.
     */
    public boolean intersecao(Segmento s) {
        Point a = s.getA();
        Point b = s.getB();

        // Calcula a projeção do centro do círculo no segmento
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        double t = ((centro.getX() - a.getX()) * dx + (centro.getY() - a.getY()) * dy) / (dx * dx + dy * dy);

        // Garante que a projeção esteja dentro do segmento [0,1]
        t = Math.max(0, Math.min(1, t));
        Point projecao = new Point(a.getX() + t * dx, a.getY() + t * dy);

        // Verifica se a projeção está dentro do círculo
        return centro.dist(projecao) <= raio;
    }


    /**
     * Verifica se o círculo colide com outra figura geométrica.
     *
     * @param outra A outra figura a ser verificada.
     * @return true se houver colisão, false caso contrário.
     */
    @Override
    public boolean colideCom(FiguraGeometrica outra) {
        if (outra instanceof Poligono) {
            return colideComPoligono((Poligono) outra);
        } else if (outra instanceof Circulo) {
            return colideComCirculo((Circulo) outra);
        }
        return false;
    }

    /**
     * Verifica se o círculo colide com outro círculo.
     * A colisão ocorre se a distância entre os centros dos círculos for menor ou igual à soma dos raios.
     *
     * @param outro O outro círculo.
     * @return true se houver colisão, false caso contrário.
     */
    private boolean colideComCirculo(Circulo outro) {
        double distanciaCentros = centro.dist(outro.getCentro());
        double somaRaios = this.raio + outro.getRaio();
        return distanciaCentros <= somaRaios;
    }

    /**
     * Retorna o raio do círculo.
     *
     * @return O valor do raio.
     */
    public double getRaio() {
        return raio;
    }

    /**
     * Verifica se o círculo colide com um polígono.
     * A colisão pode ocorrer de três formas:
     * - Se alguma aresta do polígono intersectar o círculo.
     * - Se o centro do círculo estiver dentro do polígono.
     * - Se algum dos vértices do polígono estiver dentro do círculo.
     *
     * @param poligono O polígono a ser verificado.
     * @return true se houver colisão, false caso contrário.
     */
    public boolean colideComPoligono(Poligono poligono) {
        // Verifica se alguma aresta do polígono intersecta o círculo
        for (Segmento segmento : poligono.getArestas()) {
            if (intersecao(segmento)) {
                return true;
            }
        }

        // Verifica se o centro do círculo está dentro do polígono
        if (poligono.contemPonto(this.centro)) {
            return true;
        }

        // Verifica se pelo menos um dos vértices do polígono está dentro do círculo
        for (Point vertice : poligono.getVertices()) {
            if (this.contemPonto(vertice)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Retorna um novo círculo transladado para um ponto específico.
     *
     * @param destino O ponto para onde o círculo deve ser movido.
     * @return Um novo círculo transladado.
     */
    @Override
    public Circulo translacao(Point destino) {
        this.centro = destino;

        return this;
    }

    /**
     * Verifica se um ponto está dentro do círculo.
     * Um ponto está dentro do círculo se a distância entre ele e o centro for menor ou igual ao raio.
     *
     * @param p O ponto a ser verificado.
     * @return true se o ponto estiver dentro do círculo, false caso contrário.
     */
    @Override
    public boolean contemPonto(Point p) {
        return centro.dist(p) <= raio;
    }

    public Point centro() {
        return new Point(this.centro);
    }

    public Circulo escalar(double factor){
        this.raio = this.raio * factor;
        return this;
    }

    /**
     * Retorna uma representação textual do círculo.
     *
     * @return String representando o círculo no formato "Circulo: (x,y) raio".
     */
    @Override
    public String toString() {
        return String.format("%s %.2f", centro.toString(), raio);
    }
}

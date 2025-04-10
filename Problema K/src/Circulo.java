
import java.util.List;
/**
 * Classe que representa um círculo definido por um centro e um raio.
 *
 * @author Ana Borsatti
 * @version 23-03-2025
 * @inv O raio deve ser positivo e o círculo deve estar dentro do primeiro quadrante.
 */
public class Circulo extends FiguraGeometrica {
    private double raio;
    /**
     * Construtor que recebe uma string com coordenadas e raio.
     *
     * @param coordenadas String representando as coordenadas do centro e o raio.
     * @throws IllegalArgumentException se o círculo violar a invariante.
     */
    public Circulo(String coordenadas) {
        super(coordenadasXY(coordenadas));

        String[] partes = coordenadas.trim().split(" ");
        double raio = Double.parseDouble(partes[2].trim());

        if (!invariant(super.pontos, raio))
            throw new IllegalArgumentException("Circulo:vi");
        this.raio = raio;
    }
    /**
     * Construtor que recebe uma lista de pontos e um raio.
     *
     * @param p Lista de pontos representando o centro.
     * @param raio Raio do círculo.
     * @throws IllegalArgumentException se o círculo violar a invariante.
     */
    public Circulo(List<Ponto> p, double raio) {
        super(p);
        if (!invariant(super.pontos, raio))
            throw new IllegalArgumentException("Circulo:vi");
        this.raio = raio;

    }
    /**
     * Extrai as coordenadas X e Y do centro do círculo a partir da string de entrada.
     *
     * @param coordenadas String representando as coordenadas e o raio.
     * @return String contendo apenas as coordenadas X e Y.
     */

    private static String coordenadasXY(String coordenadas) {
        String[] partes = coordenadas.trim().split(" ");
        return partes[0] + " " + partes[1];
    }
    /**
     * Verifica a invariante do círculo: raio positivo e dentro do primeiro quadrante.
     *
     * @param p Lista de pontos do centro.
     * @param raio Raio do círculo.
     * @return true se o círculo for válido, false caso contrário.
     */

    private boolean invariant(List<Ponto> p, double raio) {
        return raio > 0 && p.get(0).getX() - raio >= 0 && p.get(0).getY() - raio >= 0;
    }

    /**
     * Calcula o perímetro do círculo.
     *
     * @return O valor do perímetro.
     */

    @Override
    public double perimetro(){
        return 2 * Math.PI * raio;
    }

    /**
     * Retorna o raio do círculo.
     *
     * @return Raio do círculo.
     */

    public double getRaio (){
        return this.raio;
    }

    /**
     * Representação em string do círculo.
     *
     * @return String representando o círculo.
     */

    @Override
    public String toString(){
        return "Circulo: " + getPonto(0).toString() + " " + (int) getRaio();
    }
    /**
     * Verifica se há colisão com outra figura geométrica.
     *
     * @param that Figura geométrica a ser verificada.
     * @return true se houver colisão, false caso contrário.
     */

    @Override
    public boolean colisao(FiguraGeometrica that) {
        if (that instanceof Circulo)
            return verificaColisaoCirculo((Circulo) that);
        else if (that instanceof Poligono)
            return verificaColisaoPoligono((Poligono) that);
        return false;
    }
    /**
     * Verifica se há colisão entre o círculo e um polígono.
     *
     * @param that Polígono a ser verificado.
     * @return true se houver colisão, false caso contrário.
     */

    private boolean verificaColisaoPoligono(Poligono that) {
        int indice = 0;
        if (that instanceof Retangulo || that instanceof Triangulo)
            indice = 1;

        // Verifica se algum vértice do polígono está dentro do círculo
        for (int i = indice; i < that.getPontos().size(); i++) {
            Ponto ponto = that.getPonto(i);
            if (contemPonto(ponto)) {
                return true; // O ponto está dentro do círculo
            }
        }

        // Verifica se algum segmento do polígono intercepta o círculo
        for (int i = indice; i < that.getPontos().size(); i++) {
            int j = (i + 1) % that.getPontos().size();
            Ponto pInicio = that.getPonto(i);
            Ponto pFim = that.getPonto(j);
            Segmento segmento = new Segmento(pInicio, pFim);
            if (segmento.intersection(this) >0) {
                return true; // Colisão com o segmento
            }
        }

        // Verifica se o círculo contém o ponto do centro do polígono
        if (that.contemPonto(this.getPonto(0)))
            return true;


        return false;
    }
    /**
     * Verifica se dois círculos colidem.
     *
     * @param that Outro círculo.
     * @return true se houver colisão, false caso contrário.
     */

    public boolean verificaColisaoCirculo(Circulo that) {
        double d = this.getPonto(0).dist(that.getPonto(0));
        return d < (this.getRaio() + that.getRaio());
    }


    private boolean contemPonto(Ponto p) { //verifica se o ponto esta dentro do circulo
        double distancia = this.getPonto(0).dist(p);

        return distancia <= this.getRaio();
    }
}

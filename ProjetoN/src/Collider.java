import geometria.Circulo;
import geometria.FiguraGeometrica;
import geometria.Point;
import geometria.Poligono;

/**
 * Classe responsável por representar um Collider, que encapsula uma figura geométrica
 * (polígono ou círculo) e sua transformação no espaço.
 *
 * @author Francisco Nunes
 * @author Ana Borsatti
 * @version 30/03/2025
 * @inv O Collider sempre possui uma figura geométrica válida e uma transformação associada.
 */
public class Collider implements ICollider {
    private Transform transform;
    private FiguraGeometrica figura;

    /**
     * Constrói um Collider baseado em um conjunto de pontos para formar um polígono.
     *
     * @param pontos Array de coordenadas representando os vértices do polígono.
     * @param t Transformação associada ao Collider.
     */
    public Collider(double[] pontos, Transform t) {
        this.transform = new Transform(t);
        this.figura = new Poligono(pontos);
    }

    /**
     * Constrói um Collider baseado em um círculo com um determinado raio.
     *
     * @param raio O raio do círculo.
     * @param t Transformação associada ao Collider.
     */
    public Collider(double raio, Transform t) {
        this.transform = new Transform(t);
        double[] valores = new double[3];

        valores[0] = t.position().getX();
        valores[1] = t.position().getY();
        valores[2] = raio;
        this.figura = new Circulo(valores);
    }

    /**
     * Constrói um Collider como cópia de outro Collider.
     *
     * @param collider O Collider a ser copiado.
     */
    public Collider(Collider collider) {
        this.transform = new Transform(collider.transform);

        if (collider.getFigura() instanceof Poligono p)
            this.figura = new Poligono(p.getVerticesArray());
        else {
            Circulo circulo = (Circulo) collider.getFigura();
            double[] valores = new double[3];
            valores[0] = circulo.getCentro().getX();
            valores[1] = circulo.getCentro().getY();
            valores[2] = circulo.getRaio();
            this.figura = new Circulo(valores);
        }
    }

    /**
     * Define uma nova figura geométrica para o Collider.
     *
     * @param figura A nova figura geométrica a ser associada ao Collider.
     */
    public void setFigura(FiguraGeometrica figura) {
        this.figura = figura;
    }

    /**
     * Obtém a figura geométrica associada ao Collider.
     *
     * @return A figura geométrica associada ao Collider.
     */
    public FiguraGeometrica getFigura() {
        return this.figura;
    }

    /**
     * Obtém o centroide do Collider, que corresponde à posição da sua transformação.
     *
     * @return Um ponto representando o centroide do Collider.
     */
    @Override
    public Point centroid() {
        return transform.position();
    }

    /**
     * Retorna a representação em String do Collider, baseada na sua figura geométrica.
     *
     * @return Uma string representando o Collider.
     */
    @Override
    public String toString() {
        if (this.figura instanceof Circulo c) {
            return c.toString();
        } else if (this.figura instanceof Poligono p) {
            return p.toString();
        }
        return null;
    }


    public boolean colision(Collider that){
        if (this.figura.colideCom(that.figura) && this.transform.layer() == that.transform.layer())
            return true;
        else
            return false;
    }
}

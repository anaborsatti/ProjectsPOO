
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
    protected Transform transform;
    protected FiguraGeometrica figura;

    /**
     * Constrói um Collider baseado em um conjunto de pontos para formar um polígono.
     *
     * @param t Transformação associada ao Collider.
     */
    public Collider(Transform t) {
        this.transform = new Transform(t);
    }



    /**
     * Constrói um Collider como cópia de outro Collider.
     *
     * @param collider O Collider a ser copiado.
     */
    public Collider(Collider collider) {
        this.transform = new Transform(collider.transform);
        this.figura = collider.figura;
    }

    /**
     * Define uma nova figura geométrica para o Collider.
     *
     * @param figura A nova figura geométrica a ser associada ao Collider.
     */
    public void setFigura(FiguraGeometrica figura) {
        this.figura = figura;
    }

    public void setTransform(Transform t) {
        this.transform = t;
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


    @Override
    public void onUpdate() {

    }



    @Override
    public boolean isColliding(ICollider other) {
        if (isColliding(new CollPoly(this))) {
            (this).onUpdate();
            return true;
        }
        else if (isColliding(new CollCircle(this))) {
            (this).onUpdate();
            return true;
        }
        return false;
    }

    @Override
    public boolean isColliding(CollPoly other) {
        return figura.colideCom(other.getFigura());
    }

    @Override
    public boolean isColliding(CollCircle other) {
        return figura.colideCom(other.getFigura());
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
}

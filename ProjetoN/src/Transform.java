import geometria.Circulo;
import geometria.Point;
import geometria.FiguraGeometrica;
import geometria.Poligono;

/**
 * Classe responsável por representar uma transformação espacial de um objeto de jogo.
 * Contém informações sobre posição, camada, ângulo de rotação e escala.
 *
 * @author Francisco Nunes
 * @author Ana Borsatti
 * @version 30/03/2025
 * @inv A transformação sempre possui valores válidos para posição, camada, ângulo e escala.
 */
public class Transform implements ITransform {
    private Point position;
    private int layer;
    private double angle;
    private double scale;
    private Collider c;

    /**
     * Constrói uma transformação com posição, camada, ângulo e escala especificados.
     *
     * @param p Posição do objeto.
     * @param layer Camada do objeto.
     * @param angle Ângulo de rotação em graus.
     * @param scale Fator de escala.
     */
    public Transform(Point p, int layer, double angle, double scale) {
        this.position = new Point(p);
        this.layer = layer;
        this.angle = angle;
        this.scale = scale;
        this.c = null;
    }

    /**
     * Constrói uma transformação como cópia de outra transformação existente.
     *
     * @param t Transformação a ser copiada.
     */
    public Transform(Transform t) {
        this.position = new Point(t.position());
        this.layer = t.layer();
        this.angle = t.angle();
        this.scale = t.scale();
        this.c = t.c;
    }

    /**
     * Define o collider associado a esta transformação.
     *
     * @param c O collider a ser associado.
     */
    public void setCollider(Collider c) {
        if (c == null)
            return;

        this.c = c;
        updateCollider();
    }

    /**
     * Atualiza o collider para refletir a transformação aplicada.
     */
    public void updateCollider() {
        if (this.c == null) return;

        FiguraGeometrica figura = this.c.getFigura();

        if (figura instanceof Circulo tempCirculo) {
            tempCirculo = tempCirculo.escalar(scale).translacao(this.position);
            this.c.setFigura(tempCirculo);
        } else if (figura instanceof Poligono tempPoligono) {
            tempPoligono = tempPoligono.rotacao(this.angle);
            tempPoligono = tempPoligono.escalar(this.scale);
            this.c.setFigura(tempPoligono.translacao(this.position));
        }
    }

    /**
     * Move a transformação para uma nova posição e camada.
     *
     * @param dPos Nova posição a ser somada à atual.
     * @param dlayer Mudança na camada do objeto.
     */
    @Override
    public void move(Point dPos, int dlayer) {
        double x = dPos.getX();
        double y = dPos.getY();
        this.position = new Point(this.position.getX() + x, this.position.getY() + y);
        this.layer += dlayer;
        this.c.setFigura(this.c.getFigura().translacao(this.position));
    }

    /**
     * Rotaciona a transformação pelo ângulo especificado.
     *
     * @param dTheta Ângulo de rotação em graus.
     */
    @Override
    public void rotate(double dTheta) {
        this.angle += dTheta;

        if (this.c.getFigura() instanceof Poligono p) {
            this.c.setFigura(p.rotacao(dTheta));
        }
    }

    /**
     * Escala a transformação pelo fator especificado.
     *
     * @param dScale Fator de escala a ser aplicado.
     */
    @Override
    public void scale(double dScale) {
        this.scale += dScale;
        FiguraGeometrica figura = this.c.getFigura();

        if (figura instanceof Poligono p) {
            this.c.setFigura(p.escalar(this.scale));
        } else if (figura instanceof Circulo c) {
            this.c.setFigura(c.escalar(this.scale));
        }
    }

    /**
     * Retorna a posição atual da transformação.
     *
     * @return A posição do objeto como um ponto.
     */
    @Override
    public Point position() {
        return new Point(this.position);
    }

    /**
     * Retorna a camada na qual o objeto está inserido.
     *
     * @return O número da camada.
     */
    @Override
    public int layer() {
        return this.layer;
    }

    /**
     * Retorna o ângulo de rotação atual da transformação.
     *
     * @return O ângulo de rotação em graus.
     */
    @Override
    public double angle() {
        return this.angle;
    }

    /**
     * Retorna o fator de escala da transformação.
     *
     * @return O fator de escala.
     */
    @Override
    public double scale() {
        return this.scale;
    }

    /**
     * Retorna uma representação em string da transformação.
     *
     * @return Uma string representando a transformação.
     */
    @Override
    public String toString() {
        String points = this.position.toString();
        String angle = String.format("%.2f", angle()).replace(",", ".");
        String scale = String.format("%.2f", scale()).replace(",", ".");

        return String.format("%s %d %s %s", points, layer(), angle, scale);
    }
}

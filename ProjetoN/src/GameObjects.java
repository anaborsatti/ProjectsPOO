import geometria.Point;

/**
 * Classe responsável por representar um objeto de jogo, contendo um nome,
 * uma transformação espacial e um collider associado.
 *
 * @author Francisco Nunes
 * @author Ana Borsatti
 * @version 30/03/2025
 * @inv O GameObject sempre possui um nome, uma transformação e um collider válidos.
 */
public class GameObjects implements IGameObjects {
    private String name;
    private Transform transform;
    private Collider collider;

    /**
     * Constrói um objeto de jogo com um nome, uma transformação e um collider.
     *
     * @param name Nome do objeto.
     * @param transform Transformação associada ao objeto.
     * @param collider Collider associado ao objeto.
     */
    public GameObjects(String name, Transform transform, Collider collider) {
        this.name = name;
        this.transform = new Transform(transform);
        this.collider = new Collider(collider);
        this.transform.setCollider(this.collider);
    }

    /**
     * Escala o objeto de jogo por um fator dado.
     *
     * @param dScale Fator de escala.
     */
    public void scale(double dScale) {
        this.transform.scale(dScale);
    }

    /**
     * Rotaciona o objeto de jogo por um ângulo dado.
     *
     * @param dTheta Ângulo de rotação em graus.
     */
    public void rotate(double dTheta) {
        this.transform.rotate(dTheta);
    }

    /**
     * Move o objeto de jogo para uma nova posição e camada.
     *
     * @param dPos Nova posição.
     * @param dlayer Nova camada.
     */
    public void move(Point dPos, int dlayer) {
        this.transform.move(dPos, dlayer);
    }

    /**
     * Obtém o nome do objeto de jogo.
     *
     * @return O nome do objeto.
     */
    @Override
    public String name() {
        return this.name;
    }

    /**
     * Obtém uma cópia da transformação do objeto de jogo.
     *
     * @return Uma cópia da transformação do objeto.
     */
    @Override
    public ITransform transform() {
        return new Transform(this.transform);
    }

    /**
     * Obtém uma cópia do collider do objeto de jogo.
     *
     * @return Uma cópia do collider do objeto.
     */
    @Override
    public ICollider collider() {
        return new Collider(this.collider);
    }

    /**
     * Retorna a representação em String do objeto de jogo.
     *
     * @return Uma string representando o objeto de jogo.
     */
    @Override
    public String toString() {
        return (this.name + "\n" + this.transform + "\n" + this.collider);
    }



}
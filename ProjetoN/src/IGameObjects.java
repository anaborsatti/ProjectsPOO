public interface IGameObjects {

    /**
     * @return the name of the GameObject
     */
    String name();

    /**
     * @return the transform of the GameObject
     */
    ITransform transform();

    /**
     * @return he Collider of the GameObject with its centroid at this.transform().position()
     */
    ICollider collider();
}
import geometria.Point;

public interface ITransform {

    /**
     * Move this ITransform by dPos.x(), dPos.y() and dlayer
     * @param dPos the 2D differential to move
     * @param dlayer the layer differential
     */
    public void move(Point dPos, int dlayer);

    /**
    * Rotate this ITransform from current orientation by dTheta
    * @param dTheta
    * pos: 0 <= this.angle() < 360
    */
    public void rotate(double dTheta);

    /**
     * increment the iTransform scale by dScale
     * @param dScale
     */
    public void scale(double dScale);

    /**
     * @return the (x,y) coordinates
     */
    public Point position();

    /**
     * @return the layer
     */
    public int layer();

    /**
     * @return the angle in degrees
     */
    public double angle();

    /**
     * @return the current scale factor
     */
    public double scale();
}
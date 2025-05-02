import geometria.Point;

public interface ICollider
{
    Point centroid();

    void onUpdate();
    boolean isColliding(ICollider other);
    boolean isColliding(CollPoly other);
    boolean isColliding(CollCircle other);
}
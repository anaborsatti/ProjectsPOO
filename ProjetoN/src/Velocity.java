public class Velocity {
    private double dx, dy; // Velocity in x and y directions
    private int dlayer;    // Velocity in layer
    private double dTheta; // Rotational velocity
    private double dScale; // Scaling velocity

    public Velocity(double dx, double dy, int dlayer, double dTheta, double dScale) {
        this.dx = dx;
        this.dy = dy;
        this.dlayer = dlayer;
        this.dTheta = dTheta;
        this.dScale = dScale;
    }

    public double getDx() { return dx; }
    public double getDy() { return dy; }
    public int getDlayer() { return dlayer; }
    public double getDTheta() { return dTheta; }
    public double getDScale() { return dScale; }
}
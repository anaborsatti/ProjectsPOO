import geometria.Poligono;

public class CollPoly extends Collider {
    public CollPoly(Collider collider) {
        super(collider);
        Poligono poligono = (Poligono) collider.getFigura();
        this.figura =new Poligono(poligono.getVerticesArray());
        onUpdate();
    }
    public CollPoly(double[] pontos, Transform t) {
        super(t);
        this.figura = new Poligono(pontos);
        onUpdate();
    }

    @Override
    public void onUpdate() {
        Poligono poligono = (Poligono) super.getFigura();
        Poligono novoPoligono = new Poligono(poligono.getVerticesArray());
        novoPoligono = novoPoligono.rotacao(transform.angle());
        novoPoligono = novoPoligono.escalar(transform.scale());
        super.setFigura(novoPoligono.translacao(transform.position()));
    }
}
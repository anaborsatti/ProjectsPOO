import geometria.Circulo;
import geometria.Point;

public class CollCircle extends Collider {

    public CollCircle(Collider collider) {
        super(collider);
        Circulo c = (Circulo) collider.getFigura();
        this.figura = new Circulo(new double[]{c.getCentro().getX(), c.getCentro().getY(), c.getRaio()});
        onUpdate();
    }


    public CollCircle(double raio, Transform t) {
        super (t);
        double[] valores = new double[3];

        valores[0] = t.position().getX();
        valores[1] = t.position().getY();
        valores[2] = raio;
        this.figura = new Circulo(valores);
        onUpdate();
    }

    @Override
    public void onUpdate() {
        Circulo circulo = (Circulo) super.getFigura();
        Point centro = circulo.getCentro();
        double raio = circulo.getRaio();
        double[] dados = {centro.getX(), centro.getY(), raio};
        Circulo novoCirculo = new Circulo(dados);
        novoCirculo = novoCirculo.escalar(transform.scale());
        setFigura(novoCirculo.translacao(transform.position()));
    }
}
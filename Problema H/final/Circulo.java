

public class Circulo {
    private double raio;
    private Ponto ponto;

    public Circulo(double raio, Ponto ponto) {
        this.raio = raio;
        this.ponto = ponto;

        if (!invariant()) {
            error();
        }
    }

    private boolean invariant (){
        return raio > 0 && ponto.getX() - raio >= 0 && ponto.getY() - raio >= 0;
    }

    private void error() {
        System.out.println("Circulo:vi");
        System.exit(0);
    }

    public double perimetro(){
        return 2 * Math.PI * raio;
    }

    public double getRaio (){
        return this.raio;
    }
    public Ponto getPonto(){
        return this.ponto;
    }
}

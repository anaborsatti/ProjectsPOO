

public class Circulo extends FiguraGeometrica {
    private double raio;
    private Ponto ponto;

    public Circulo(String coordenadas) {
        String[] partes = coordenadas.trim().split(" ");
        int x = Integer.parseInt(partes[0].trim());
        int y = Integer.parseInt(partes[1].trim());
        double raio = Double.parseDouble(partes[2].trim());

        this.ponto = new Ponto(x, y);
        this.raio = raio;
        if (!invariant())
            throw new IllegalArgumentException("Circulo:vi");
    }


    private boolean invariant(){
        return raio > 0 && ponto.getX() - raio >= 0 && ponto.getY() - raio >= 0;
    }

    @Override
    public double perimetro(){
        return 2 * Math.PI * raio;
    }


    public double getRaio (){
        return this.raio;
    }
    public Ponto getPonto(){
        return this.ponto;
    }

    @Override
    public String toString(){
        return "Circulo: " + getPonto().toString() + " " + (int) getRaio();
    }
}

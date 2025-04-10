
import java.util.List;

public class Circulo extends FiguraGeometrica {
    private double raio;

    public Circulo(String coordenadas) {
        super(coordenadasXY(coordenadas));

        String[] partes = coordenadas.trim().split(" ");
        double raio = Double.parseDouble(partes[2].trim());

        if (!invariant(super.pontos, raio))
            throw new IllegalArgumentException("Circulo:vi");
        this.raio = raio;
    }

    public Circulo(List<Ponto> p, double raio) {
        super(p);
        if (!invariant(super.pontos, raio))
            throw new IllegalArgumentException("Circulo:vi");
        this.raio = raio;

    }

    private static String coordenadasXY(String coordenadas) {
        String[] partes = coordenadas.trim().split(" ");
        return partes[0] + " " + partes[1];
    }


    private boolean invariant(List<Ponto> p, double raio) {
        return raio > 0 && p.get(0).getX() - raio >= 0 && p.get(0).getY() - raio >= 0;
    }

    @Override
    public double perimetro(){
        return 2 * Math.PI * raio;
    }


    public double getRaio (){
        return this.raio;
    }


    @Override
    public String toString(){
        return "Circulo: " + getPonto(0).toString() + " " + (int) getRaio();
    }
}

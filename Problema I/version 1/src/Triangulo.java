import java.util.*;

public class Triangulo extends Poligono {


    public Triangulo(String coordenadas) {
        super(coordenadas);
        if (!invariant())
            throw new IllegalArgumentException("Triangulo:vi");
    }


    private boolean invariant(){
        return pontos.size() == 3  && desigualdadeTriangular();
    }


    private boolean desigualdadeTriangular() {
        double a = pontos.get(0).dist(pontos.get(1));
        double b = pontos.get(1).dist(pontos.get(2));
        double c = pontos.get(2).dist(pontos.get(0));
        return a + b > c && a + c > b && b + c > a && a > 0 && b > 0 && c > 0;
    }

    

    @Override
    public String toString() {
        return "Triangulo: " + pontos.toString() ;
    }


}

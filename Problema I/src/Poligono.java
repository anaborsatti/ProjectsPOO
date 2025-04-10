import java.util.*;

public class Poligono extends FiguraGeometrica{

    protected List<Segmento> arestas;


    public Poligono(String coordenadas) {
        super (coordenadas);
        if (!invariant(this.pontos))
            throw new IllegalArgumentException("Poligono:vi");
        this.arestas = createAresta(this.pontos);

    }


    private boolean invariant (List<Ponto> p) {
        return p.size() >= 3 && !interception(p) && !colinear(p);
    }


    private boolean interception(List<Ponto> p) {
        List<Segmento> aresta = createAresta(p);
        for (int i = 0; i < aresta.size(); i++) {
            for (int j = i + 1; j < aresta.size(); j++) {
                if (aresta.get(i).intersecta(aresta.get(j))) {
                    return true;
                }
            }
        }
        if (aresta.get(0).intersecta(aresta.get(aresta.size() - 1))) {
            return true;
        }
        return false;
    }


    private boolean colinear(List<Ponto> p) {
        for (int i = 0; i < p.size() - 2; i++) {
            for (int j = i + 1; j < p.size() - 1; j++) {
                for (int k = j + 1; k < p.size(); k++) {
                    if (p.get(i).colinear(p.get(j), p.get(k))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private List<Segmento> createAresta(List<Ponto> p) {
        List<Segmento> arestas = new ArrayList<>();
        for (int i = 0; i < p.size() - 1; i++) {
            arestas.add(new Segmento(p.get(i), p.get(i + 1)));
        }
        arestas.add(new Segmento(p.get(p.size() - 1), p.get(0)));
        return arestas;
    }

    @Override
    public String toString() {
        return "Poligono de " + pontos.size() + " vertices: " + pontos.toString();
    }

    public double perimetro(){
        double perimetro = 0;
        for (int i = 0; i < arestas.size(); i++) {
            perimetro += arestas.get(i).dist();
        }
        return perimetro;
    }
}

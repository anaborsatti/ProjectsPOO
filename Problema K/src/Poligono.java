import java.util.*;

/**
 * Classe que representa um polígono, uma figura geométrica composta por uma lista de pontos (vértices) e arestas (segmentos).
 * @author Ana Clara Borsatti
 * @version 23/03/25
 * @inv Um polígono deve ter pelo menos 3 vértices, não pode ter arestas que se interceptam e não pode ter pontos colineares.
 */
public class Poligono extends FiguraGeometrica {

    protected List<Segmento> arestas;

    /**
     * Construtor que cria um polígono a partir de uma string de coordenadas.
     * @param coordenadas String contendo as coordenadas dos pontos do polígono.
     * @throws IllegalArgumentException Se a invariante do polígono não for respeitada.
     */
    public Poligono(String coordenadas) {
        super(coordenadas);
        if (!invariant(this.pontos))
            throw new IllegalArgumentException("Poligono:vi");
        this.arestas = createAresta(this.pontos);
    }

    /**
     * Construtor que cria um polígono a partir de uma lista de pontos.
     * @param p Lista de pontos que definem os vértices do polígono.
     * @throws IllegalArgumentException Se a invariante do polígono não for respeitada.
     */
    public Poligono(List<Ponto> p) {
        super(p);
        if (!invariant(this.pontos))
            throw new IllegalArgumentException("Poligono:vi");
        this.arestas = createAresta(this.pontos);
    }

    /**
     * Verifica a invariante do polígono: deve ter pelo menos 3 vértices, não pode ter arestas que se interceptam e não pode ter pontos colineares.
     * @param p Lista de pontos que definem os vértices do polígono.
     * @return true se a invariante for respeitada, false caso contrário.
     */
    private boolean invariant(List<Ponto> p) {
        return p.size() >= 3 && !interception(p) && !colinear(p);
    }

    /**
     * Verifica se há interseção entre as arestas do polígono.
     * @param p Lista de pontos que definem os vértices do polígono.
     * @return true se houver interseção entre as arestas, false caso contrário.
     */
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

    /**
     * Verifica se há pontos colineares no polígono.
     * @param p Lista de pontos que definem os vértices do polígono.
     * @return true se houver pontos colineares, false caso contrário.
     */
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

    /**
     * Cria as arestas do polígono a partir dos pontos fornecidos.
     * @param p Lista de pontos que definem os vértices do polígono.
     * @return Lista de segmentos que representam as arestas do polígono.
     */
    private List<Segmento> createAresta(List<Ponto> p) {
        List<Segmento> arestas = new ArrayList<>();
        for (int i = 0; i < p.size() - 1; i++) {
            arestas.add(new Segmento(p.get(i), p.get(i + 1)));
        }
        arestas.add(new Segmento(p.get(p.size() - 1), p.get(0)));
        return arestas;
    }

    /**
     * Retorna uma representação em string do polígono.
     * @return String que descreve o polígono, incluindo o número de vértices e suas coordenadas.
     */
    @Override
    public String toString() {
        return "Poligono de " + pontos.size() + " vertices: " + pontos.toString();
    }

    /**
     * Calcula o perímetro do polígono.
     * @return O perímetro do polígono, somando o comprimento de todas as arestas.
     */
    public double perimetro() {
        double perimetro = 0;
        for (int i = 0; i < arestas.size(); i++) {
            perimetro += arestas.get(i).dist();
        }
        return perimetro;
    }

    /**
     * Verifica colisão entre dois polígonos, checando interseções entre arestas e se um polígono
     * contém pontos do outro. Se a figura for um círculo, delega a verificação para o método do círculo.
     * @param p Ponto a ser verificado.
     * @return true se o ponto estiver dentro do polígono, false caso contrário.
     */
    public boolean contemPonto(Ponto p) {
        int intersecoes = 0;

        // Verifica se o ponto está em alguma aresta do polígono
        for (Segmento s : arestas) {
            if (s.onSegment(p, s.getA(), s.getB()))
                return true;
        }

        // Algoritmo Ray-Casting para verificar se o ponto está dentro do polígono
        int n = pontos.size();
        int indice = 0;
        if (!(this instanceof Retangulo || this instanceof Triangulo))
            indice = 1;

        for (int i = indice; i < n; i++) {
            Ponto a = pontos.get(i);
            Ponto b = pontos.get((i + 1) % n);
            if ((a.getY() > p.getY()) != (b.getY() > p.getY())) {
                double xIntersecao = a.getX() + (double) ((b.getX() - a.getX()) * (p.getY() - a.getY())) / (b.getY() - a.getY());
                if (p.getX() < xIntersecao) {
                    intersecoes++;
                }
            }
        }

        return (intersecoes % 2) == 1; // Ímpar → dentro, Par → fora
    }

    /**
     * Verifica se há colisão entre este polígono e outra figura geométrica.
     * @param p Figura geométrica a ser verificada.
     * @return true se houver colisão, false caso contrário.
     */
    @Override
    public boolean colisao(FiguraGeometrica p) {
        if (p instanceof Poligono) {
            Poligono poligono = (Poligono) p;

            for (Segmento aresta1 : this.getArestas()) {
                for (Segmento aresta2 : poligono.getArestas()) {
                    if (aresta1.intersecta(aresta2)) {
                        return true;
                    }
                }
            }

            for (Ponto ponto : this.getPontos()) {
                if (poligono.contemPonto(ponto)) {
                    return true;
                }
            }

            for (Ponto ponto : poligono.getPontos()) {
                if (this.contemPonto(ponto)) {
                    return true;
                }
            }
        }
        if (p instanceof Circulo)
            return p.colisao(this);

        return false;
    }

    /**
     * Retorna a lista de arestas do polígono.
     * @return Lista de segmentos que representam as arestas do polígono.
     */
    private List<Segmento> getArestas() {
        return this.arestas;
    }
}
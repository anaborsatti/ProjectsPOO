
import java.util.*;

/**
 * Representa um retângulo definido por quatro pontos em coordenadas polares.
 * Os pontos devem formar ângulos retos consecutivos para garantir a estrutura retangular.
 *
 * @author Ana Borsatti
 * @version 2025-03-09
 * @inv O retângulo deve ser formado por exatamente quatro pontos distintos que formam ângulos retos.
 */
public class Retangulo {
    private List<Ponto> pontos;

    /**
     * Construtor que recebe uma lista de quatro pontos que definem um retângulo.
     *
     * @param pontos Lista de pontos que representam os vértices do retângulo.
     * @throws IllegalArgumentException Se a lista não tiver exatamente quatro pontos ou não formar um retângulo válido.
     */
    public Retangulo(List<Ponto> pontos) {
        if (pontos.size() != 4)
            throw new IllegalArgumentException("Retangulo:vi");
        if (!invariant(pontos))
            throw new IllegalArgumentException("Retangulo:vi");
        this.pontos = new ArrayList<>(pontos);
    }

    /**
     * Verifica se os pontos fornecidos formam um retângulo válido.
     *
     * @param pontos Lista de pontos a ser verificada.
     * @return true se os pontos formam um retângulo, false caso contrário.
     */
    private boolean invariant(List<Ponto> pontos) {
        return pontos.get(0).formaAnguloReto(pontos.get(1), pontos.get(2)) &&
                pontos.get(1).formaAnguloReto(pontos.get(2), pontos.get(3)) &&
                pontos.get(2).formaAnguloReto(pontos.get(3), pontos.get(0)) &&
                pontos.get(3).formaAnguloReto(pontos.get(0), pontos.get(1));
    }

    /**
     * Representação textual do retângulo, mostrando os seus quatro vértices.
     *
     * @return Uma string contendo as coordenadas dos vértices do retângulo.
     */
    @Override
    public String toString() {
        return "["
                + "(" + pontos.get(0).getX() + "," + pontos.get(0).getY() + "), "
                + "(" + pontos.get(1).getX() + "," + pontos.get(1).getY() + "), "
                + "(" + pontos.get(2).getX() + "," + pontos.get(2).getY() + "), "
                + "(" + pontos.get(3).getX() + "," + pontos.get(3).getY() + ")]";
    }

    /**
     * Verifica se um segmento de reta fornecido intersecta alguma das arestas do retângulo.
     *
     * @param segmento O segmento a ser testado para interseção.
     * @return true se houver interseção com alguma aresta do retângulo, false caso contrário.
     */
    public boolean intersection(Segmento segmento) {
        Segmento a1 = new Segmento(pontos.get(0), pontos.get(1));
        Segmento a2 = new Segmento(pontos.get(1), pontos.get(2));
        Segmento a3 = new Segmento(pontos.get(2), pontos.get(3));
        Segmento a4 = new Segmento(pontos.get(3), pontos.get(0));
        return segmento.intersecta(a1) || segmento.intersecta(a2) || segmento.intersecta(a3) || segmento.intersecta(a4);
    }
}

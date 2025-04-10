import java.util.*;

/**
 * Representa um retângulo definido por quatro pontos em coordenadas polares.
 * Os pontos devem formar ângulos retos consecutivos para garantir a estrutura retangular.
 *
 * @author Ana Borsatti
 * @version 23/03/25
 * @inv O retângulo deve ser formado por exatamente quatro pontos distintos que formam ângulos retos.
 */
public class Retangulo extends Poligono {

    /**
     * Construtor que cria um retângulo a partir de uma string de coordenadas.
     *
     * @param coordenadas String contendo as coordenadas dos pontos do retângulo.
     * @throws IllegalArgumentException Se a invariante do retângulo não for respeitada.
     */
    public Retangulo(String coordenadas) {
        super(coordenadas);
        if (!invariant())
            throw new IllegalArgumentException("Retangulo:vi");
    }

    /**
     * Construtor que cria um retângulo a partir de uma lista de pontos.
     *
     * @param p Lista de pontos que definem os vértices do retângulo.
     * @throws IllegalArgumentException Se a invariante do retângulo não for respeitada.
     */
    public Retangulo(List<Ponto> p) {
        super(p);
        if (!invariant())
            throw new IllegalArgumentException("Retangulo:vi");
    }

    /**
     * Verifica a invariante do retângulo: deve ter exatamente quatro pontos que formam ângulos retos consecutivos.
     *
     * @return true se a invariante for respeitada, false caso contrário.
     */
    private boolean invariant() {
        return pontos.get(0).formaAnguloReto(pontos.get(1), pontos.get(2)) &&
                pontos.get(1).formaAnguloReto(pontos.get(2), pontos.get(3)) &&
                pontos.get(2).formaAnguloReto(pontos.get(3), pontos.get(0)) &&
                pontos.get(3).formaAnguloReto(pontos.get(0), pontos.get(1)) && pontos.size() == 4;
    }

    /**
     * Retorna uma representação textual do retângulo, mostrando os seus quatro vértices.
     *
     * @return Uma string contendo as coordenadas dos vértices do retângulo.
     */
    @Override
    public String toString() {
        return "Retangulo: " + pontos.toString();
    }

}
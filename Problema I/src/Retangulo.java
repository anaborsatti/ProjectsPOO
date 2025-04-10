
/**
 * Representa um retângulo definido por quatro pontos em coordenadas polares.
 * Os pontos devem formar ângulos retos consecutivos para garantir a estrutura retangular.
 *
 * @author Ana Borsatti
 * @version 2025-03-09
 * @inv O retângulo deve ser formado por exatamente quatro pontos distintos que formam ângulos retos.
 */
public class Retangulo extends Poligono {

    public Retangulo(String coordenadas) {
        super(coordenadas);
        if (!invariant())
            throw new IllegalArgumentException("Retangulo:vi");
    }





    private boolean invariant() {
        return pontos.get(0).formaAnguloReto(pontos.get(1), pontos.get(2)) &&
                pontos.get(1).formaAnguloReto(pontos.get(2), pontos.get(3)) &&
                pontos.get(2).formaAnguloReto(pontos.get(3), pontos.get(0)) &&
                pontos.get(3).formaAnguloReto(pontos.get(0), pontos.get(1)) && pontos.size() == 4;
    }

    /**
     * Representação textual do retângulo, mostrando os seus quatro vértices.
     *
     * @return Uma string contendo as coordenadas dos vértices do retângulo.
     */
    @Override
    public String toString() {
        return "Retangulo: " + pontos.toString() ;
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

import java.util.ArrayList;
import java.util.List;
/**
 * Classe que representa uma figura geométrica genérica.
 * Esta classe serve como base para outras figuras geométricas, como retângulos, triângulos e círculos.
 *
 * @author Ana Borsatti
 * @version 23/03/25
 */
public  abstract  class FiguraGeometrica {
    protected final List<Ponto> pontos;
    /**
     * Construtor que cria uma figura geométrica a partir de uma string de coordenadas.
     *
     * @param coordenadas  A string contendo as coordenadas dos pontos que definem a figura.
     */
    public FiguraGeometrica(String coordenadas){
        String[] partes = coordenadas.split(" ");
        int numVertices;
        if (this instanceof Retangulo)
            numVertices = 4;
        else if (this instanceof Triangulo)
            numVertices = 3;
        else if (this instanceof Circulo)
            numVertices = 1;
        else
            numVertices = Integer.parseInt(partes[0]);
        List<Ponto> p = new ArrayList<>();
        for (int i = (this instanceof Retangulo || this instanceof Triangulo || this instanceof Circulo ? 0 : 1); i < partes.length; i += 2) {
            p.add(new Ponto(Integer.parseInt(partes[i]), Integer.parseInt(partes[i + 1])));
        }
        this.pontos = p;

    }    /**
     * Construtor que cria uma figura geométrica a partir de uma lista de pontos.
     *
     * @param p  A lista contendo os pontos que definem a figura.
     */


    public FiguraGeometrica(List <Ponto> p){
        this.pontos = p;
    }
    /**
     * Método abstrato que deve ser implementado pelas subclasses para calcular o perímetro.
     *
     * @return O valor do perímetro da figura.
     */

    public abstract double perimetro();
    /**
     * Método abstrato que deve ser implementado pelas subclasses para representar a figura como uma string.
     *
     * @return A representação em string da figura.
     */
    public abstract String toString();
    /**
     * Obtém os pontos que definem a figura geométrica.
     *
     * @return A lista de pontos que definem a figura.
     */
    public List<Ponto> getPontos() {
        return pontos;
    }

    public Ponto getPonto(int i) {
        return pontos.get(i);
    }

    public int getX (int i){
        return pontos.get(i).getX();
    }
    public int getY (int i){
        return pontos.get(i).getY();
    }

    /**
     * Realiza a translação dos pontos da figura geométrica por um deslocamento (dx, dy).
     *
     * @param dx Deslocamento no eixo X.
     * @param dy Deslocamento no eixo Y.
     * @return A lista de pontos após a translação.
     */
    public List<Ponto> translacao(int dx, int dy) {
        List<Ponto> result = new ArrayList<>();

        for (Ponto p : this.pontos) {
            Ponto novo = new Ponto(p.getX() + dx, p.getY() + dy);
            result.add(novo);
        }

        return result;
    }

    /**
     * Verifica se há colisão entre duas figuras geométricas.
     * Este método é abstrato e deve ser implementado pelas subclasses.
     *
     * @param that A figura geométrica com a qual se deseja verificar a colisão.
     * @return true se houver colisão, false caso contrário.
     */
    public abstract boolean colisao (FiguraGeometrica that);


}

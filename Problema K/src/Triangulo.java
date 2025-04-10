import java.util.List;

/**
 * Classe que representa um Triângulo, que é uma subclasse de Polígono.
 * Verifica a validade dos pontos para garantir que formam um triângulo válido.
 *
 * @author Ana Borsatti
 * @version 23/03/25
 */
public class Triangulo extends Poligono {

    /**
     * Construtor que cria um triângulo a partir de uma string de coordenadas.
     * A string é interpretada para criar pontos, e a validade do triângulo é verificada.
     *
     * @param coordenadas String contendo as coordenadas dos pontos do triângulo.
     * @throws IllegalArgumentException Se o triângulo não for válido (não tem 3 pontos ou não satisfaz a desigualdade triangular).
     */
    public Triangulo(String coordenadas) {
        super(coordenadas);
        if (!invariant())
            throw new IllegalArgumentException("Triangulo:vi");
    }

    /**
     * Construtor que cria um triângulo a partir de uma lista de pontos.
     * A lista de pontos é verificada para garantir que formam um triângulo válido.
     *
     * @param p Lista de pontos que definem o triângulo.
     * @throws IllegalArgumentException Se o triângulo não for válido (não tem 3 pontos ou não satisfaz a desigualdade triangular).
     */
    public Triangulo(List<Ponto> p) {
        super(p);
        if (!invariant())
            throw new IllegalArgumentException("Triangulo:vi");
    }

    /**
     * Método privado que verifica a invariância do triângulo.
     * Um triângulo válido precisa ter exatamente 3 pontos e satisfazer a desigualdade triangular
     * (a soma das distâncias de dois lados quaisquer deve ser sempre maior do que a distância do terceiro lado).
     *
     * @return true se a invariância for atendida, caso contrário false.
     */
    private boolean invariant() {
        return pontos.size() == 3 && desigualdadeTriangular();
    }

    /**
     * Método que verifica se os pontos fornecidos satisfazem a desigualdade triangular.
     * A desigualdade triangular afirma que a soma das distâncias de quaisquer dois lados de um triângulo deve ser maior que o terceiro lado.
     *
     * @return true se os pontos formam um triângulo válido, caso contrário false.
     */
    private boolean desigualdadeTriangular() {
        double a = pontos.get(0).dist(pontos.get(1));
        double b = pontos.get(1).dist(pontos.get(2));
        double c = pontos.get(2).dist(pontos.get(0));

        return a + b > c && a + c > b && b + c > a && a > 0 && b > 0 && c > 0;
    }

    /**
     * Método que retorna uma representação em string do triângulo.
     *
     * @return Uma string representando o triângulo e seus pontos.
     */
    @Override
    public String toString() {
        return "Triangulo: " + pontos.toString();  // Retorna a string formatada com os pontos do triângulo
    }

}

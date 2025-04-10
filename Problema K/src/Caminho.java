/**
 * Classe que representa um caminho composto por uma sequência de pontos.
 * A classe permite calcular a distância total percorrida ao longo do caminho.
 *
 * @author Ana Borsatti
 * @version 23-03-2025
 */
public class Caminho {

    private Ponto[] caminho;  // Array de pontos que define o caminho.

    /**
     * Construtor que recebe um array de pontos para definir o caminho.
     *
     * @param pontos Array de pontos que define o caminho a ser percorrido.
     */
    public Caminho(Ponto[] pontos) {
        this.caminho = pontos;  // Inicializa o array de pontos do caminho.
    }

    /**
     * Calcula a distância total percorrida ao longo do caminho.
     * A distância total é a soma das distâncias entre pontos consecutivos no caminho.
     *
     * @return A distância total percorrida.
     */
    public double dist() {
        double result = 0;  // Variável para armazenar a distância total.

        // Loop para somar as distâncias entre pontos consecutivos.
        for (int i = 0; i < this.caminho.length - 1; i++) {
            result += caminho[i].dist(caminho[i + 1]);  // Adiciona a distância entre os pontos i e i+1.
        }

        return result;  // Retorna a distância total percorrida.
    }

}

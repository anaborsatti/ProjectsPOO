

public class Caminho {

    private Ponto[] caminho;

    public Caminho (Ponto[] pontos) {
        this.caminho = pontos;

    }

    public double dist () {
        double result = 0;
        for (int i = 0; i < this.caminho.length - 1; i++) {
             result += caminho[i].dist(caminho[i+1]);
        }
        return result;
    }

}

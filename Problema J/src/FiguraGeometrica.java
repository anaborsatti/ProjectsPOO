import java.util.ArrayList;
import java.util.List;

public  abstract  class FiguraGeometrica {
    protected final List<Ponto> pontos;
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

    }

    public FiguraGeometrica(List <Ponto> p){
        this.pontos = p;
    }



    public abstract double perimetro();
    public abstract String toString();
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

    public List<Ponto> translacao(int dx, int dy) {
        List<Ponto> result = new ArrayList<>();

        for (Ponto p : this.pontos) {
            Ponto novo = new Ponto(p.getX() + dx, p.getY() + dy);
            result.add(novo);
        }

        return result;
    }


}

package geometria;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa um polígono genérico.
 * Recebe um conjunto de doubles com coordenadas e valida o polígono.
 */
public class Poligono extends  FiguraGeometrica{
    protected List<Point> vertices;
    protected List<Segmento> arestas;

    /**
     * Construtor da classe Poligono.
     * Recebe um conjunto de pontos contendo as coordenadas dos pontos, valida e cria os segmentos (arestas).
     *
     * @param valores um conjunto de pares de doubles que formam um poligono
     * @throws IllegalArgumentException Se o polígono for inválido.
     */
    public Poligono(double[] valores) {
        super(valores);
        List<Point> points = criarPontos(valores);
        check(points);
        this.vertices = points;
        this.arestas = criaArestas(points);
    }

    public static List<Point> criarPontos(double[] valores) {
        List<Point> pontos = new ArrayList<>();
        for (int i = 0; i < valores.length; i += 2) {
            pontos.add(new Point(valores[i], valores[i + 1]));
        }
        return pontos;
    }


//    /**
//     * Converte a string com as coordenadas para uma lista de pontos.
//     * Detecta automaticamente se o primeiro valor é um número de vértices ou uma coordenada.
//     *
//     * @param pontosString A string com as coordenadas dos pontos.
//     * @return Lista de pontos.
//     * @throws IllegalArgumentException Se o número de pontos for inválido.
//     */
//    protected static List<Point> parsePontos(String pontosString) {
//        List<Point> pontos = new ArrayList<>();
//        String[] coordenadas = pontosString.split("\\s+");
//
//        // Detectar se o primeiro número é um contador de vértices ou uma coordenada
//        int startIndex = 0;
//        if (coordenadas.length % 2 == 1) { // Número total de valores é ímpar => primeiro número é o contador de vértices
//            startIndex = 1;
//            int numVertices = Integer.parseInt(coordenadas[0]);
//        }
//
//        // Converte a string para uma lista de pontos
//        for (int i = startIndex; i < coordenadas.length; i += 2) {
//            int x = Integer.parseInt(coordenadas[i]);
//            int y = Integer.parseInt(coordenadas[i + 1]);
//            pontos.add(new Point(x, y));
//        }
//
//        return pontos;
//    }


    /**
     * Cria os segmentos (arestas) a partir dos pontos do polígono.
     *
     * @param pontos Lista de pontos que define o polígono.
     * @return Lista de segmentos (arestas).
     */
    private List<Segmento> criaArestas(List<Point> pontos) {
        List<Segmento> arestas = new ArrayList<>();
        for (int i = 0; i < pontos.size(); i++) {
            Point ponto1 = pontos.get(i);
            Point ponto2 = pontos.get((i + 1) % pontos.size()); // o último ponto se conecta ao primeiro
            Segmento aresta = new Segmento(ponto1, ponto2);
            arestas.add(aresta);
        }
        return arestas;
    }

    /**
     * Verifica se o polígono tem ao menos 3 pontos e se não existem três pontos colineares consecutivos.
     *
     * @param pontos Lista de pontos a ser verificada.
     */
    private void check(List<Point> pontos) {
        if (pontos.size() < 3) {
            System.out.println("Poligono:vi");
            System.exit(0);
        }

        // Verifica se há pontos colineares consecutivos
        for (int i = 0; i < pontos.size(); i++) {
            Point p1 = pontos.get(i);
            Point p2 = pontos.get((i + 1) % pontos.size());
            Point p3 = pontos.get((i + 2) % pontos.size());

            new Segmento(p1, p2);
            if (Segmento.colinear(p1, p2, p3)) {
                System.out.println("Poligono:vi");
                System.exit(0);
            }
        }

        List<Segmento> arestas = criaArestas(pontos);

        for (int i = 0; i < arestas.size(); i++) {
            for (int j = i + 1; j < arestas.size(); j++) {
                Segmento s1 = arestas.get(i);
                Segmento s2 = arestas.get(j);
                if (s1.intersecta(s2)) {
                    System.out.println("Poligono:vi");
                    System.exit(0);
                }
            }
        }
    }


    /**
     * Calcula o perímetro do polígono, somando os perímetros de todos os segmentos.
     *
     * @return O perímetro do polígono.
     */
    public double perimetro() {
        double perimetro = 0.0;
        for (int i = 0; i < vertices.size(); i++) {
            Point ponto1 = vertices.get(i);
            Point ponto2 = vertices.get((i + 1) % vertices.size()); // o último ponto se conecta ao primeiro
            perimetro += ponto1.dist(ponto2);
        }
        return perimetro;
    }

    /**
     * Retorna a lista de segmentos (arestas) do polígono.
     *
     * @return A lista de segmentos.
     */
    public List<Segmento> getArestas() {
        return arestas;
    }

    /**
     * Retorna a lista de vértices do polígono.
     *
     * @return Lista contendo os pontos que formam os vértices do polígono.
     */
    public List<Point> getVertices() {
        return vertices;
    }

    /**
     * Retorna um array de coordenadas dos vértices do polígono.
     *
     * @return Um array de doubles representando os vértices do polígono.
     */
    public double[] getVerticesArray() {
        double[] coordenadas = new double[vertices.size() * 2];
        for (int i = 0; i < vertices.size(); i++) {
            coordenadas[i * 2] = vertices.get(i).getX();
            coordenadas[i * 2 + 1] = vertices.get(i).getY();
        }
        return coordenadas;
    }

    /**
     * Verifica se este polígono colide com outra figura geométrica.
     * A colisão pode ocorrer se houver interseção de arestas ou se um dos polígonos estiver contido no outro.
     *
     * @param other A outra figura geométrica a ser testada.
     * @return true se houver colisão, false caso contrário.
     */
    @Override
    public boolean colideCom(FiguraGeometrica other) {
        if (other instanceof Poligono) {
            return colideComPoligono((Poligono) other);
        } else if (other instanceof Circulo) {
            return colideComCirculo((Circulo) other);
        }
        return false;
    }

    /**
     * Verifica se este polígono colide com outro polígono.
     * A colisão ocorre se houver interseção entre alguma das arestas ou se um polígono estiver contido dentro do outro.
     *
     * @param other O outro polígono a ser verificado.
     * @return true se houver colisão, false caso contrário.
     */
    private boolean colideComPoligono(Poligono other) {
        // Verifica se alguma aresta dos dois polígonos se intersecta
        for (Segmento s1 : this.arestas) {
            for (Segmento s2 : other.getArestas()) {
                if (s1.intersecta(s2)) {
                    return true;
                }
            }
        }
        // Se não houver interseção entre as arestas, verifica se um polígono contém um dos vértices do outro
        return this.contemPonto(other.vertices.get(0)) || other.contemPonto(this.vertices.get(0));
    }

    /**
     * Verifica se este polígono colide com um círculo.
     * A colisão ocorre se o círculo interceptar alguma aresta do polígono ou se o centro do círculo estiver dentro do polígono.
     *
     * @param circulo O círculo a ser testado.
     * @return true se houver colisão, false caso contrário.
     */
    private boolean colideComCirculo(Circulo circulo) {
        return circulo.colideComPoligono(this);
    }


    /**
     * Verifica se um ponto está dentro do polígono usando o algoritmo do raio.
     * Esse algoritmo funciona traçando um raio horizontal a partir do ponto e contando
     * quantas vezes ele intersecta as arestas do polígono.
     *
     * Se o número de interseções for ímpar, o ponto está dentro do polígono.
     * Se for par, o ponto está fora.
     *
     * @param p O ponto a ser testado.
     * @return true se o ponto estiver dentro do polígono, false caso contrário.
     */
    @Override
    public boolean contemPonto(Point p) {
        int intersecoes = 0;

        // Cria um segmento representando um "raio" horizontal que se estende infinitamente para a direita
        Segmento raio = new Segmento(p, new Point(10000, p.getY())); // Ponto extremo arbitrariamente grande

        // Conta quantas arestas do polígono intersectam o raio
        for (Segmento s : this.arestas) {
            if (s.intersecta(raio)) {
                intersecoes++;
            }
        }

        // Se o número de interseções for ímpar, o ponto está dentro; se for par, está fora
        return (intersecoes % 2) == 1;
    }


    /**
     * Retorna um novo polígono transladado para um ponto específico.
     *
     * @param destino O ponto para onde o polígono deve ser movido.
     * @return Um novo polígono transladado.
     */
    @Override
    public Poligono translacao(Point destino) {
        Point centro = this.Centro();
        double dx = destino.getX() - centro.getX();
        double dy = destino.getY() - centro.getY();

        List<Point> novosVertices = new ArrayList<>();
        for (Point vertice : vertices) {
            novosVertices.add(new Point(vertice.getX() + dx, vertice.getY() + dy));
        }

        this.vertices = novosVertices;

        return this;
    }

    /**
     * Calcula o centroide do polígono usando a fórmula do centroide para polígonos.
     * O centroide é calculado com base na área e nos produtos cruzados dos vértices.
     *
     * @return Um ponto representando o centro do polígono.
     */
    public Point Centro() {
        double area = 0.0;
        double xCentro = 0.0;
        double yCentro = 0.0;
        int n = vertices.size();
        double x0;
        double x1;
        double y0;
        double y1;
        double produtoEscalar;

        for (int i = 0; i < n; i++) {
            x0 = vertices.get(i).getX();
            y0 = vertices.get(i).getY();
            x1 = vertices.get((i + 1) % n).getX();
            y1 = vertices.get((i + 1) % n).getY();

            produtoEscalar = x0 * y1 - x1 * y0;
            area += produtoEscalar;
            xCentro += (x0 + x1) * produtoEscalar;
            yCentro += (y0 + y1) * produtoEscalar;
        }

        area /= 2.0;
        xCentro /= (6.0 * area);
        yCentro /= (6.0 * area);

        if (xCentro == -0.0) xCentro = 0.0;
        if (yCentro == -0.0) yCentro = 0.0;

        return new Point(xCentro, yCentro);
    }

    /**
     * Retorna um novo polígono apos a rotacao em torno do seu centroide por um ângulo teta (em graus).
     *
     * @param teta O ângulo de rotação em graus.
     * @return Um novo polígono apos a rotacao.
     */
    public Poligono rotacao(double teta) {
        double radianos = Math.toRadians(teta); // Converter graus para radianos
        Point centro = this.Centro();
        double cx = centro.getX();
        double cy = centro.getY();
        double cosT = Math.cos(radianos);
        double sinT = Math.sin(radianos);
        List<Point> novosVertices = new ArrayList<>();
        for (Point vertice : vertices) {
            double x = vertice.getX() - cx;
            double y = vertice.getY() - cy;

            double novoX = Math.round(x * cosT - y * sinT + cx);
            double novoY = Math.round(x * sinT + y * cosT + cy);

            novosVertices.add(new Point(novoX, novoY));
        }

        this.vertices = novosVertices;
        return this;
    }

    /**
     * Retorna um novo polígono escalado em relação ao seu centroide.
     *
     * @param escala O fator de escala.
     * @return Um novo polígono escalado.
     */
    public Poligono escalar(double escala) {
        Point centro = this.Centro();
        double cx = centro.getX();
        double cy = centro.getY();
        List<Point> novosVertices = new ArrayList<>();

        for (Point vertice : vertices) {
            double novoX = (cx + (vertice.getX() - cx) * escala);
            double novoY = (cy + (vertice.getY() - cy) * escala);
            novosVertices.add(new Point(novoX, novoY));
        }

        this.vertices = novosVertices;
        return this;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vertices.size(); i++) {
            sb.append(vertices.get(i).toString());
            if (i < vertices.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

}

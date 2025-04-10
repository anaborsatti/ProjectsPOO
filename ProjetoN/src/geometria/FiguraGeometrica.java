package geometria;

/**
 * Classe abstrata que representa uma figura geométrica genérica.
 */
public abstract class FiguraGeometrica {
    protected static final double EPS = 1e-9;

    /**
     * Construtor que recebe uma string de argumentos para inicializar a figura.
     * Cada subclasse deve implementar sua própria lógica de parsing.
     *
     * @param valores Conjunto de valores necessários para construir a figura.
     */
    public FiguraGeometrica(double[] valores) {
    }

    /**
     * Método abstrato que deve ser implementado para calcular o perímetro da figura.
     *
     * @return O valor do perímetro da figura geométrica.
     */
    public abstract double perimetro();


    /**
     * Verifica se esta figura colide com outra figura.
     *
     * @param outra A outra figura geométrica.
     * @return true se houver colisão, false caso contrário.
     */
    public abstract boolean colideCom(FiguraGeometrica outra);

    /**
     * Verifica se um ponto está dentro da figura geométrica.
     *
     * @param p O ponto a verificar.
     * @return true se o ponto estiver dentro, false caso contrário.
     */
    public abstract boolean contemPonto(Point p);

    public abstract FiguraGeometrica translacao(Point destino);

    public abstract FiguraGeometrica escalar(double factor);

}

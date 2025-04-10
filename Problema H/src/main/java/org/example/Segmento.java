package org.example;
/**
 * Classe que representa um segmento de reta definido por dois pontos.
 * Realiza operações como verificação de interseção entre segmentos e interseção com círculos.
 *
 * @author Ana Borsatti
 * @version 09 Mar 2025
 */
public class Segmento {
    private Ponto a;  // Ponto inicial do segmento
    private Ponto b;  // Ponto final do segmento

    /**
     * Construtor que cria um segmento de reta a partir de dois pontos.
     *
     * @param a O ponto inicial do segmento.
     * @param b O ponto final do segmento.
     * @throws IllegalArgumentException Se os dois pontos forem iguais, o segmento é inválido.
     */
    public Segmento(Ponto a, Ponto b) {
        if (a.equals(b)) {
            throw new IllegalArgumentException("Segmento:vi");
        }
        this.a = a;
        this.b = b;
    }

    /**
     * Verifica se dois segmentos se interceptam.
     * Realiza o cálculo dos parâmetros de interseção usando determinante e parâmetros t e u.
     *
     * @param that O outro segmento de reta a ser comparado.
     * @return true se os segmentos se intersectam, caso contrário false.
     */
    public boolean intersecta(Segmento that) {
        int d = calcularDeterminante(that);

        if (d == 0) {
            // Verifica se são colineares e têm sobreposição válida
            boolean sobrepoe = (onSegment(that.a, this.a, this.b) ||
                    onSegment(that.b, this.a, this.b) ||
                    onSegment(this.a, that.a, that.b) ||
                    onSegment(this.b, that.a, that.b));

            return sobrepoe;
        }

        double t = calcularT(that, d);
        double u = calcularU(that, d);

        return (t > 0.0 && t < 1.0) && (u > 0.0 && u < 1.0);
    }

    /**
     * Calcula o determinante usado para verificar a interseção dos segmentos.
     *
     * @param that O outro segmento a ser comparado.
     * @return O valor do determinante.
     */
    private int calcularDeterminante(Segmento that) {
        int x1 = a.getX(), y1 = a.getY();
        int x2 = b.getX(), y2 = b.getY();
        int x3 = that.a.getX(), y3 = that.a.getY();
        int x4 = that.b.getX(), y4 = that.b.getY();

        return (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
    }

    /**
     * Calcula o parâmetro t para determinar a interseção de dois segmentos.
     *
     * @param that O outro segmento.
     * @param d O determinante calculado.
     * @return O valor de t.
     */
    private double calcularT(Segmento that, int d) {
        int x1 = a.getX(), y1 = a.getY();
        int x3 = that.a.getX(), y3 = that.a.getY();
        int x4 = that.b.getX(), y4 = that.b.getY();

        return ((x3 - x1) * (y4 - y3) - (y3 - y1) * (x4 - x3)) / (double) d;
    }

    /**
     * Calcula o parâmetro u para determinar a interseção de dois segmentos.
     *
     * @param that O outro segmento.
     * @param d O determinante calculado.
     * @return O valor de u.
     */
    private double calcularU(Segmento that, int d) {
        int x1 = a.getX(), y1 = a.getY();
        int x2 = b.getX(), y2 = b.getY();
        int x3 = that.a.getX(), y3 = that.a.getY();

        return ((x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1)) / (double) d;
    }

    /**
     * Verifica se um ponto c está dentro do segmento ab (para colinearidade).
     *
     * @param c O ponto a ser verificado.
     * @param a O ponto inicial do segmento.
     * @param b O ponto final do segmento.
     * @return true se o ponto c estiver dentro do segmento ab, caso contrário false.
     */
    private boolean onSegment(Ponto c, Ponto a, Ponto b) {
        return (c.getX() > Math.min(a.getX(), b.getX()) && c.getX() < Math.max(a.getX(), b.getX()) &&
                c.getY() > Math.min(a.getY(), b.getY()) && c.getY() < Math.max(a.getY(), b.getY()));
    }

    /**
     * Verifica a interseção entre o segmento e um círculo.
     *
     * @param c O círculo a ser comparado.
     * @return 1 se houver interseção, 0 caso contrário.
     */
    public int intersection(Circulo c) {
        if (isVertical()) {
            return intersectionVerticalLine(c);
        }

        double m = calculateM();
        double bReta = calculateYIntercept(m);

        double A = calculateA(m);
        double B = calculateB(m, bReta, c);
        double C = calculateC(bReta, c);

        double discriminante = calculateDiscriminant(A, B, C);

        return discriminante >= 0 ? 1 : 0;
    }

    /**
     * Verifica se a reta do segmento é vertical.
     *
     * @return true se a reta for vertical, caso contrário false.
     */
    private boolean isVertical() {
        return a.getX() == b.getX();
    }

    /**
     * Calcula a interseção de uma reta vertical com um círculo.
     *
     * @param c O círculo a ser comparado.
     * @return 1 se houver interseção, 0 caso contrário.
     */
    private int intersectionVerticalLine(Circulo c) {
        int x1 = a.getX();
        double h = c.getPonto().getX();
        double r = c.getRaio();

        if (Math.abs(x1 - h) <= r)
            return 1; // Há interseção
        else
            return 0; // Não há interseção
    }

    /**
     * Calcula a inclinação (m) da reta que passa pelos pontos a e b.
     *
     * @return O valor da inclinação m.
     */
    private double calculateM() {
        int x1 = a.getX();
        int y1 = a.getY();
        int x2 = b.getX();
        int y2 = b.getY();
        return (double) (y2 - y1) / (x2 - x1);
    }

    /**
     * Calcula o intercepto no eixo y da reta.
     *
     * @param m O valor da inclinação da reta.
     * @return O intercepto no eixo y (bReta).
     */
    private double calculateYIntercept(double m) {
        int x1 = a.getX();
        int y1 = a.getY();
        return y1 - m * x1;
    }

    /**
     * Calcula o coeficiente A da equação quadrática usada para verificar a interseção com o círculo.
     *
     * @param m O valor da inclinação da reta.
     * @return O valor de A.
     */
    private double calculateA(double m) {
        return 1 + Math.pow(m, 2);
    }

    /**
     * Calcula o coeficiente B da equação quadrática usada para verificar a interseção com o círculo.
     *
     * @param m O valor da inclinação da reta.
     * @param bReta O intercepto da reta no eixo y.
     * @param c O círculo a ser comparado.
     * @return O valor de B.
     */
    private double calculateB(double m, double bReta, Circulo c) {
        double h = c.getPonto().getX();
        double k = c.getPonto().getY();
        return 2 * (m * (bReta - k) - h);
    }

    /**
     * Calcula o coeficiente C da equação quadrática usada para verificar a interseção com o círculo.
     *
     * @param bReta O intercepto da reta no eixo y.
     * @param c O círculo a ser comparado.
     * @return O valor de C.
     */
    private double calculateC(double bReta, Circulo c) {
        double h = c.getPonto().getX();
        double k = c.getPonto().getY();
        double r = c.getRaio();
        return Math.pow(h, 2) + Math.pow(bReta - k, 2) - Math.pow(r, 2);
    }

    /**
     * Calcula o discriminante da equação quadrática usada para verificar a interseção com o círculo.
     *
     * @param A O coeficiente A da equação quadrática.
     * @param B O coeficiente B da equação quadrática.
     * @param C O coeficiente C da equação quadrática.
     * @return O valor do discriminante.
     */
    private double calculateDiscriminant(double A, double B, double C) {
        return Math.pow(B, 2) - 4 * A * C;
    }
}

package org.example;

import java.util.*;
/**
 * Classe Cliente para testar a interseção entre um retângulo e um segmento de reta.
 * Recebe entrada do usuário para definir os pontos do retângulo e do segmento.
 *
 * @author Ana Borsatti
 * @version 2025-03-09
 */
public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] coordenadasRetangulo = new int[8];
        for (int i = 0; i < 8; i++) {
            coordenadasRetangulo[i] = scanner.nextInt();
        }

        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();

        try {
            List<Ponto> pontosRetangulo = new ArrayList<>();
            for (int i = 0; i < 8; i += 2) {
                pontosRetangulo.add(new Ponto(coordenadasRetangulo[i], coordenadasRetangulo[i + 1]));
            }

            Retangulo retangulo = new Retangulo(pontosRetangulo);

            Segmento segmento = new Segmento(new Ponto(x1, y1), new Ponto(x2, y2));

            if (retangulo.intersection(segmento))
                System.out.println(1);
            else
                System.out.println(0);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

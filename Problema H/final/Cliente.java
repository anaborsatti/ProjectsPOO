
import java.util.*;

public class Cliente {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        int[] coordenadas = new int[8];
        for (int i = 0; i < 8; i++) {
            coordenadas[i] = scanner.nextInt();
        }

        try {
            List<Ponto> pontos = new ArrayList<>();
            for (int i = 0; i < 8; i += 2) {
                pontos.add(new Ponto(coordenadas[i], coordenadas[i + 1]));
            }

            Retangulo retangulo = new Retangulo(pontos);
            System.out.println(retangulo.toString());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

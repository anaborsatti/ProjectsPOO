import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * Classe Cliente que gerencia a criação de figuras geométricas e verifica colisões entre elas.
 * O programa lê entradas do usuário, cria objetos de figuras geométricas e verifica se há colisões.
 *
 * @author Ana Borsatti
 * @version 23-03-2025
 */
public class Cliente {

    /**
     * Capitaliza (alterar o formato de uma palavra de modo que a primeira letra se torne maiúscula e as demais letras sejam minúsculas)
     * a primeira letra de uma string e coloca as demais em minúsculas.
     *
     * @param s String a ser capitalizada.
     * @return A string com a primeira letra em maiúscula e as demais em minúscula.
     */
    public static String capital(String s) {
        if (s == null || s.isEmpty())
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    /**
     * Verifica se há colisões entre figuras geométricas em uma lista.
     * Compara cada figura da lista com todas as outras.
     *
     * @param figuras Lista de figuras geométricas.
     * @return Uma mensagem indicando a posição da colisão ou "Sem colisões" se não houver colisões.
     */
    public static String verificarColisoes(List<FiguraGeometrica> figuras) {
        for (int i = 0; i < figuras.size(); i++) {
            for (int j = i + 1; j < figuras.size(); j++) {
                if (figuras.get(i).colisao(figuras.get(j))) {
                    return "Colisao na posicao " + i;
                }
            }
        }
        return "Sem colisoes";
    }

    /**
     * Método principal que recebe a entrada do usuário e cria instâncias de figuras geométricas
     * com base nas entradas fornecidas. Verifica também as colisões entre as figuras.
     *
     * @param args Argumentos de linha de comando (não utilizados neste caso).
     * @throws Exception Em caso de erro ao tentar criar uma figura ou ao verificar colisões.
     */
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Constructor<?> constructor;
        Class<?> cl;
        FiguraGeometrica f;
        String s;
        String[] aos;
        List<FiguraGeometrica> figuras = new ArrayList<>();
        int n = 0;


        while (sc.hasNextLine()) {
            s = sc.nextLine();
            if (s.isEmpty())
                break;
            aos = s.split(" ", 2);

            try {
                cl = Class.forName(capital(aos[0]));
                constructor = cl.getConstructor(String.class);
                f = (FiguraGeometrica) constructor.newInstance(aos[1]);
                figuras.add(f);
                n++;
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Não foi encontrada a classe: " + cnfe.getMessage());
            } catch (Exception e) {
                figuras.clear();
                if (e.getCause() != null) {
                    System.out.println(e.getCause().getMessage());
                    break;
                } else {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }

        sc.close();


        if (n == figuras.size() && !figuras.isEmpty()) {
            String result = verificarColisoes(figuras);
            System.out.println(result);
        }
    }
}

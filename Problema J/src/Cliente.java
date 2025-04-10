import java.lang.reflect.Constructor;
import java.util.Scanner;
import java.util.List;

public class Cliente {

    public static String capital(String s) {
        if (s == null || s.isEmpty())
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Constructor<?> constructor;
        Class<?> cl;
        FiguraGeometrica f;
        String s;
        String[] aos;

        while (sc.hasNextLine()) {
            s = sc.nextLine();
            if (s.isEmpty())
                break;
            aos = s.split(" ", 2);

            try {
                cl = Class.forName(capital(aos[0]));
                constructor = cl.getConstructor(String.class);
                f = (FiguraGeometrica) constructor.newInstance(aos[1]);

                int dx = sc.nextInt();
                int dy = sc.nextInt();

                List<Ponto> pontosTransladados = f.translacao(dx, dy);

                if (cl.getName().endsWith("Circulo")) {
                    // Get the radius from the original circle
                    double raio = ((Circulo)f).getRaio();

                    Constructor<?> construtorNovo = cl.getConstructor(List.class, double.class);
                    FiguraGeometrica t = (FiguraGeometrica) construtorNovo.newInstance(pontosTransladados, raio);
                    System.out.println(t);
                } else {
                    Constructor<?> construtorNovo = cl.getConstructor(List.class);
                    FiguraGeometrica t = (FiguraGeometrica) construtorNovo.newInstance(pontosTransladados);
                    System.out.println(t);
                }

            } catch (ClassNotFoundException cnfe) {
                System.out.println("Não foi encontrada a classe: " + cnfe.getMessage());
            } catch (Exception e) {
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
    }
}
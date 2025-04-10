import java.lang.reflect.Constructor;
import java.util.Scanner;
import java.util.*;

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
        List<FiguraGeometrica> figuras = new ArrayList<>();


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
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Não foi encontrada a classe: " + cnfe.getMessage());
            } catch (Exception e) {
                figuras.clear();
                if (e.getCause() != null) {
                    System.out.println(e.getCause().getMessage());
                    break;
                }
                else {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }

        sc.close();

        if (figuras.size() >= 3) {}
            for (FiguraGeometrica figura : figuras) {
                System.out.println(figura);
            }

    }
}
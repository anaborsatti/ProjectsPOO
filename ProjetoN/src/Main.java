import geometria.Point;

import java.util.Scanner;


/**
 * Programa cliente para criar um game object
 *
 * @author [Francisco Nunes]
 * @author [Ana Borsatti]
 * @version 30/03/2025
 */

public class Main
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        GameObjects gameObject = null;

        String nome = sc.nextLine();
        if (nome.isEmpty()) System.exit(0);

        String line = sc.nextLine();
        if (line.isEmpty()) System.exit(0);

        try
        {
            String[] parts = line.split(" ");
            Point p = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
            int layer = Integer.parseInt(parts[2]);
            double angle = Double.parseDouble(parts[3]);
            double scale = Double.parseDouble(parts[4]);

            Transform t = new Transform(p, layer, angle, scale);

            line = sc.nextLine();
            if (line.isEmpty()) System.exit(0);

            parts = line.split(" ");
            double[] valores = new double[parts.length];

            for (int i = 0; i < parts.length; i++) {
                valores[i] = Double.parseDouble(parts[i]);
            }

            Collider c;

            if (parts.length == 3){
                c = new Collider(Double.parseDouble(parts[2]), t);
            }else{
                c = new Collider(valores, t);
            }

            gameObject = new GameObjects(nome, t, c);

            while (sc.hasNextLine())
            {
                line = sc.nextLine();
                if (line.isEmpty()) break;

                parts = line.split(" ");
                String command = parts[0];
                if (command.equals("move"))
                {
                    Point dPos = new Point(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                    int dlayer = Integer.parseInt(parts[3]);
                    gameObject.move(dPos, dlayer);
                }
                else if (command.equals("rotate"))
                {

                    double dTheta = Double.parseDouble(parts[1]);
                    gameObject.rotate(dTheta);
                }
                else if (command.equals("scale"))
                {
                    double dScale = Double.parseDouble(parts[1]);
                    gameObject.scale(dScale);
                }
            }


            System.out.println(gameObject);
            System.exit(0);

        } catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
import geometria.Point;
import java.util.ArrayList;
import java.util.List;
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
        List<GameObjects> gameObjects = new ArrayList<>();



        try
        {
            int frames = Integer.parseInt(sc.nextLine()); // Number of frames to simulate
            int n = Integer.parseInt(sc.nextLine()); // Number of GameObjects

            for(int i = 0; i < n; i++){
                String nome = sc.nextLine();
                if (nome.isEmpty()) System.exit(0);

                String line = sc.nextLine();
                if (line.isEmpty()) System.exit(0);

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

                for (int j = 0; j < parts.length; j++) {
                    valores[j] = Double.parseDouble(parts[j]);
                }

                Collider c;

                if (parts.length == 3){
                    c = new CollCircle(Double.parseDouble(parts[2]), t);
                }else{
                    c = new CollPoly(valores, t);
                }

                line = sc.nextLine();
                if (line.isEmpty()) System.exit(0);

                String[] velocityData = line.split(" ");
                double dx = Double.parseDouble(velocityData[0]);
                double dy = Double.parseDouble(velocityData[1]);
                int dlayer = Integer.parseInt(velocityData[2]);
                double dTheta = Double.parseDouble(velocityData[3]);
                double dScale = Double.parseDouble(velocityData[4]);
                Velocity velocity = new Velocity(dx, dy, dlayer, dTheta, dScale);
                t.setVelocity(velocity);

                gameObjects.add(new GameObjects(nome, t, c));
            }

            GameEngine gameEngine = new GameEngine();
            for (GameObjects go : gameObjects) {
                gameEngine.add(go);
            }

            gameEngine.simulateFrames(frames);

        } catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
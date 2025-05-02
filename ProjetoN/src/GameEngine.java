import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import geometria.Point;


public class GameEngine implements IGameEngine {
    @Override
    public void addEnabled() {

    }

    @Override
    public void addDisabled() {

    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean isDisabled() {
        return false;
    }

    @Override
    public List<IGameObject> getEnabled() {
        return List.of();
    }

    @Override
    public List<IGameObject> getDisabled() {
        return List.of();
    }

    private List<GameObjects> gameObjects;

    public GameEngine() {
        this.gameObjects = new ArrayList<>();
    }

    public void add(GameObjects go) {
        if (go != null && !gameObjects.contains(go)) {
            gameObjects.add(go);
        }
    }

    public void destroy(GameObjects go) {
        gameObjects.remove(go);
    }

    public List<GameObjects> getGameObjects() {
        return new ArrayList<>(gameObjects);
    }

    /**
     * Simulates the game for a given number of frames.
     * Updates positions, rotations, scales, and colliders of GameObjects and detects collisions.
     *
     * @param frames Number of frames to simulate.
     */
    public void simulateFrames(int frames) {
        for (int f = 0; f < frames; f++) {
            for (int i = 0; i < gameObjects.size(); i++) {
                GameObjects go = gameObjects.get(i); // Reference to the actual object in the list
                Transform transform = (Transform) go.transform();
                Velocity velocity = transform.getVelocity();
                if (velocity != null) {
                    // Update position, rotation, and scale
                    transform.move(new Point(velocity.getDx(), velocity.getDy()), velocity.getDlayer());
                    transform.rotate(velocity.getDTheta());
                    transform.scale(velocity.getDScale());

                    go.setTransform(transform);
                }
            }
        }
        // Detect collisions after updating all GameObjects
        detectCollisions();
    }

    /**
     * Detects collisions between GameObjects in the same layer.
     */
    public void detectCollisions() {
        Map<String, List<String>> collisions = new LinkedHashMap<>();

        // Inicializa o mapa de colisões
        for (GameObjects go : gameObjects) {
            collisions.put(go.name(), new ArrayList<>());
        }

        // Detecta colisões
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObjects go1 = gameObjects.get(i);
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObjects go2 = gameObjects.get(j);

                if (go1.transform().layer() == go2.transform().layer() &&
                        ((Collider)go1.collider()).getFigura().colideCom(((Collider)go2.collider()).getFigura())) {
                    collisions.get(go1.name()).add(go2.name());
                    collisions.get(go2.name()).add(go1.name());
                }
            }
        }

        // Imprime o resultado
        for (Map.Entry<String, List<String>> entry : collisions.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                System.out.println(entry.getKey() + " " + String.join(" ", entry.getValue()));
            }
        }
    }
}

//colliders especificos, lista de layers, melhorar testes unitarios, tirar instaceof
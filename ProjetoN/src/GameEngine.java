import java.util.List;

public class GameEngine {
    private List<GameObjects> gameObjects;
    public GameEngine(List<GameObjects> gameObjects) {

    }


    public void add (GameObjects go) {
        gameObjects.add(go);
    }

    public void destroy (GameObjects go) {
        for (GameObjects gameObject : gameObjects) {
            if (gameObject.equals(go)) {
                gameObjects.remove(gameObject);
            }
        }
    }
}

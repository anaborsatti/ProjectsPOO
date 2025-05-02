import java.util.List;

public interface IBehaviour {

    public IGameObject gameObject();
    void onInit();
    void onEnabled();
    void onDisabled();
    void onDestroy();
    void onUpdate();
    void onUpdate(double dT, IInputEvent ie);
    void onCollision(List<IGameObject> go);
}

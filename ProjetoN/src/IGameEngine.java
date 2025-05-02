import java.util.List;

public interface IGameEngine {
    void addEnabled();
    void addDisabled();

    public void enable();
    public void disable();

    public boolean isEnabled();
    public boolean isDisabled();

    public List<IGameObject> getEnabled();
    public List<IGameObject> getDisabled();
}

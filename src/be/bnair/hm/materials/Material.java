package be.bnair.hm.materials;

public class Material {
    private Type type;

    public enum Type
    {
        Or, Cuir
    }
    public Material(Type type)
    {
        this.type = type;
    }

    public Type GetMatType() {
        return type;
    }
}

package assets;

public class Grass  {
    private Vector2D position;
    public  Grass(Vector2D position){
        this.position = position;
    }
    public static double energy;

    public Vector2D getPosition() {
        return position;
    }
}

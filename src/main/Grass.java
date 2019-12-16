package main;

public class Grass implements Iorganism {
    private Vector2D position;
    public void Grass(Vector2D position){
        this.position = position;
    }
    public static double energy;
    @Override
    public Vector2D getPosition() {
        return position;
    }
    public void setEnerrgy(int value){
        energy = value;
    }
}

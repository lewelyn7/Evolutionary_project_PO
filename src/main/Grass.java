package main;

public class Grass implements Iorganism {
    private Vector2D position;
    public  Grass(Vector2D position){
        this.position = position;
    }
    public static double energy;
    @Override
    public Vector2D getPosition() {
        return position;
    }
    public void setEnergy(int value){
        energy = value;
    }
}

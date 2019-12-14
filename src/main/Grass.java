package main;

public class Grass implements Iorganism {
    final private Vector2D position;
    public void Grass(Vector2D position){
        this.position = position;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }
}

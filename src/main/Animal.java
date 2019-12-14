package main;

public class Animal implements Iorganism {
    private Vector2D position;
    private Orientation orientation;
    private Genom gens;
    private int energy;

    public Animal(Vector2D position, Orientation orientation, Genom gens, int energy) {
        this.position = position;
        this.orientation = orientation;
        this.gens = gens;
        this.energy = energy;
    }



}

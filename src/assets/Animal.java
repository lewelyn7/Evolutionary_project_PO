package assets;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Animal implements Comparable {
    private Vector2D position;
    private Orientation orientation;
    private Genom gens;
    private double energy;
    private List<IPositionChangedObserver> observers = new LinkedList<>();
    private WholeMap map;

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy){
        this.energy = energy;
    }

    public boolean decrementEnergy(){
        energy = energy - 1;
        if(energy < 1) {
            energy = 0;
            return false;
        }
        return true;
    }

    public Animal(WholeMap map,Vector2D position, Orientation orientation, Genom gens, double energy) {
        this.position = position;
        this.orientation = orientation;
        this.gens = gens;
        this.energy = energy;
        this.map = map;
    }
    public void addObserver(IPositionChangedObserver obs){
        this.observers.add(obs);
    }
    public void removeObserver(IPositionChangedObserver obs){
        this.observers.remove(obs);
    }
    void positionChanged(Vector2D oldPosition){
        for(IPositionChangedObserver obs: observers){
            obs.positionChanged(oldPosition, this);
        }
    }
    public void moveByTranslation(Vector2D translateVect){

        Vector2D oldPostion = position;
        position = fixPosition(position.add(translateVect), map.xSize, map.ySize);
        positionChanged(oldPostion);
    }

    public void moveRandomly(){

        orientation.addValue(gens.getRandomGen());
        moveByTranslation(orientation.getVector());
    }

    public Vector2D getPosition(){
        return position;
    }


    public int compareTo(Object one){
        return Double.compare(this.energy, ((Animal)one).getEnergy());
    }

    public void addEnergy(double value){
        this.energy += value;
    }

    @Override
    public String toString(){
        return "Animal: " + position.toString() + " energy " + energy + "gens " + gens.toString();
    }

    private Vector2D fixPosition(Vector2D position, int xSize, int ySize){
        int fixedX = position.x;
        int fixedY = position.y;
        if(position.x < 0){
            fixedX = xSize + position.x;
        }
        if(position.y < 0){
            fixedY = ySize + position.y;
        }
        if(position.x >= xSize) {
            fixedX = position.x % xSize;
        }
        if(position.y >= ySize) {
            fixedY = position.y % ySize;
        }
        return new Vector2D(fixedX, fixedY);
    }

    public Animal procreate(Animal other){
        Random generator = new Random();
        //find free field
        Vector2D childPosition = null;
        Orientation childOrientation = new Orientation( generator.nextInt(8));
        for(int i = 0; i < 8; i++){
            childPosition = this.getPosition().add(Orientation.getVector(i));
            if(map.canPlaceAnimal(childPosition)){
               break;
            }
        }
        this.setEnergy(this.getEnergy()*0.75);
        other.setEnergy(other.getEnergy()*0.75);
        return new Animal(map, childPosition, orientation, gens.mixGenoms(other.gens), this.getEnergy()*0.25 + other.getEnergy()*0.25);

    }


    public static Animal generateRandomAnimal(WholeMap map, double startEnergy){
        Random posGenerator = new Random();
        Vector2D position;
        do {
            position = new Vector2D(posGenerator.nextInt(map.xSize), posGenerator.nextInt(map.ySize));
        }while(!map.canPlaceAnimal(position));

        return new Animal(map, position, new Orientation(posGenerator.nextInt(8)), Genom.getRandomGenom(), startEnergy);
    }
}

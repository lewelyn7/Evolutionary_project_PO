package main;


import java.lang.reflect.GenericArrayType;
import java.util.*;

public class WholeMap implements IPositionChangedObserver {
    public int xSize;
    public int ySize;
    public float jungleRatio;
    MultiMap<Vector2D, Animal> animals = new MultiMap<>();
    Map<Vector2D, Grass> plants = new HashMap<Vector2D, Grass>();


    public void WholeMap(int xSize, int ySize){
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public boolean placeAnimal(Animal bat){
///       if(!canPlaceAnimal(bat.getPosition())){
//            return  false;
//        }
        return animals.putOne(bat.getPosition(), bat);
    }

    public PriorityQueue<Animal> animalsAt(Vector2D position){
        if(animals.get(position) != null && !animals.get(position).isEmpty()){
            return animals.get(position);
        }else
            return null;
    }
    public Grass grassAt(Vector2D position){
        return plants.get(position);

    }

    public boolean canPlant(Vector2D position){
        Grass grass = grassAt(position);
        return (grass != null);
    }
    public boolean plantGrass(Grass grass){
        if(canPlant(grass.getPosition())){
            plants.put(grass.getPosition(), grass);
            return true;
        }
        return false;
    }

    public void positionChanged( Vector2D oldPosition, Animal animal){
        animals.removeOne(oldPosition, animal);
        animals.putOne(animal.getPosition(), animal);
    }

    public void SimulateOneDay(){
        Set animalsSet = animals.entrySet();
        List<Grass> toBeEaten = new LinkedList<Grass>();

        for(Object s: animalsSet){
            Animal sanimal = (Animal)s;
            sanimal.decrementEnergy();
            if(sanimal.getEnergy() == 0) {
                animals.removeOne(sanimal.getPosition(), sanimal);
                continue;
            }
            sanimal.moveRandomly();


            if(plants.get(sanimal.getPosition()) != null){
                toBeEaten.add(plants.get(sanimal.getPosition()));
            }




        }

        //Grass eating
        for(Grass sgrass: toBeEaten){
            PriorityQueue<Animal> willBeEating = animals.get(sgrass.getPosition());

            if(willBeEating.size() == 1)
                willBeEating.peek().addEnergy(sgrass.energy);
            else {
                List<Animal> willBeEatingEqually = new LinkedList<Animal>();
                willBeEatingEqually.add(willBeEating.poll());

                int divider = 1;
                while(willBeEating.peek() != null && willBeEatingEqually.get(0).getEnergy() == willBeEating.peek().getEnergy()){
                    divider++;
                    willBeEatingEqually.add(willBeEating.poll());
                }
                for(Animal sanimal: willBeEatingEqually){
                    sanimal.addEnergy(sgrass.energy/divider);
                    willBeEating.add(sanimal);
                }
            }

            plants.remove(sgrass.getPosition());
        }

    }

}

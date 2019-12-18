package main;


import java.lang.reflect.GenericArrayType;
import java.time.Period;
import java.util.*;

public class WholeMap implements IPositionChangedObserver {
    public int xSize;
    public int ySize;
    public double jungleRatio;
    public MultiMap<Vector2D, Animal> animals = new MultiMap<Vector2D, Animal>();
    public Map<Vector2D, Grass> plants = new HashMap<Vector2D, Grass>();
    public List<Animal> animalsList = new LinkedList<Animal>();
    public JungleMap jungle;
    private int objCounter = 0;
    private int capacity;
    Random xGenerator = new Random();
    Random yGenerator = new Random();


    public WholeMap(int xSize, int ySize, double grassEnergy, double jungleRatio){
        this.jungleRatio = jungleRatio;
        this.xSize = xSize;
        this.ySize = ySize;
        this.capacity = xSize*ySize;
        Grass.energy = grassEnergy;
        jungle = new JungleMap(
                new Vector2D((int)(xSize/2 - xSize*jungleRatio/2),(int)(ySize/2 - ySize*jungleRatio/2)),
                new Vector2D((int)(xSize/2 + xSize*jungleRatio/2),(int)(ySize/2 + ySize*jungleRatio/2)),
                (int)(xSize*jungleRatio),
                (int)(ySize*jungleRatio));
    }

    public boolean placeAnimal(Animal animal){
       if(!canPlaceAnimal(animal.getPosition())){
            return  false;
        }
        animal.addObserver(this);
        animalsList.add(animal);
        animals.putOne(animal.getPosition(), animal);
        if(jungle.contains(animal.getPosition()))  jungle.objCounter++;
        objCounter++;
        return true;
    }

    public void removeAnimal(Animal animal){
        animalsList.remove(animal);
        animals.removeOne(animal.getPosition(), animal);
        objCounter--;
        if(jungle.contains(animal.getPosition()))  jungle.objCounter--;
    }
    public boolean canPlaceAnimal(Vector2D position){
        return (!animals.containsKey(position));
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
        return (grass == null);
    }
    public boolean plantGrass(Grass grass){
        if(canPlant(grass.getPosition())){
            plants.put(grass.getPosition(), grass);
            objCounter++;
            if(jungle.contains(grass.getPosition()))  jungle.objCounter++;
            return true;
        }
        return false;
    }

    public void removeGrass(Grass grass){
        plants.remove(grass);
        if(jungle.contains(grass.getPosition()))  jungle.objCounter--;
        objCounter--;
    }
    public void positionChanged( Vector2D oldPosition, Animal animal){

        animals.removeOne(oldPosition, animal);
        animals.putOne(animal.getPosition(), animal);

    }

    private Vector2D getRandomVect(){
        return new Vector2D(xGenerator.nextInt(xSize), yGenerator.nextInt(ySize));
    }



    private void plantGrassRandomly(){
        Vector2D vectToPlant = getRandomVect();
        if(capacity <= objCounter)
            throw new RuntimeException("za duzo grasss");
        while(!canPlant(vectToPlant) || jungle.contains(vectToPlant)){
            vectToPlant = getRandomVect();
        }
        plantGrass(new Grass(vectToPlant));
    }
    private void plantJungleGrassRandomly(){
        Vector2D vectToPlant = jungle.getRandomVect();
        if(jungle.capacity <= jungle.objCounter)
            throw new RuntimeException("za duzo grasss");
        while(!canPlant(vectToPlant)){
            vectToPlant = jungle.getRandomVect();
        }
        plantGrass(new Grass(vectToPlant));
    }
    public void simulateOneDay(){

        Set<Grass> toBeEaten = new HashSet<Grass>();
        List<Animal> deadAnimals = new LinkedList<Animal>();

        for(Animal sanimal : animalsList){

                sanimal.decrementEnergy();
                if (sanimal.getEnergy() == 0) {
                    deadAnimals.add(sanimal);
                    continue;
                }
                sanimal.moveRandomly();


                if (plants.get(sanimal.getPosition()) != null) {
                    toBeEaten.add(plants.get(sanimal.getPosition()));
                }


            }

        for(Animal sanimal : deadAnimals){
            removeAnimal(sanimal);
        }
        //Grass eating
        for(Grass sgrass: toBeEaten){
            PriorityQueue<Animal> willBeEating = animals.get(sgrass.getPosition());
            if(willBeEating.size() == 1) {
                willBeEating.peek().addEnergy(sgrass.energy);
            }else {
                List<Animal> willBeEatingEqually = new LinkedList<Animal>();
                willBeEatingEqually.add(willBeEating.poll());

                int divider = 1;
                while(willBeEating.peek() != null && willBeEatingEqually.get(0).getEnergy() == willBeEating.peek().getEnergy()){
                    divider++;
                    willBeEatingEqually.add(willBeEating.poll());
                }
                for(Animal sanimal: willBeEatingEqually){
                    sanimal.addEnergy(sgrass.energy/(double)divider);
                    willBeEating.add(sanimal);
                }
            }
            removeGrass(sgrass);
        }

        plantJungleGrassRandomly();
        plantGrassRandomly();

    }

}

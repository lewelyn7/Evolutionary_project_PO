package main;

import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class World {
    public static void main(String[] args){
        WholeMap map = new WholeMap(100, 100,5, 0.1, 20.0);

        Animal fox = new Animal(map,new Vector2D(0,0),new Orientation(0),new Genom(new int[32]), 10.0);
        Animal bat = new Animal(map,new Vector2D(1,1),new Orientation(0),new Genom(new int[32]), 10.0);
        map.placeAnimal(new Animal(map,new Vector2D(1,1),new Orientation(0),new Genom(new int[32]), 10.0));
        map.placeAnimal(fox);
        map.placeAnimal(new Animal(map,new Vector2D(1,1),new Orientation(0),new Genom(new int[32]), 15.0));
//        drawAnimals((Graphics2D)g,map);

        Iterator<Animal> ttt = map.animals.iterator();
        for(Object s : map.animals){
            System.out.println(s.toString());
        }
    }
}

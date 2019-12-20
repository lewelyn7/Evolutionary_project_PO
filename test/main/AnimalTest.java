package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    public void eatEqually() {
        WholeMap map = new WholeMap(50,50,5, 0.1, 10.0);
        Animal fox = new Animal(map, new Vector2D(51,50), new Orientation(6), new Genom(fillGenom(0)), 11 );
        Animal bat = new Animal(map, new Vector2D(49,50), new Orientation(2), new Genom(fillGenom(0)), 11 );
        map.placeAnimal(fox);
        map.placeAnimal(bat);
        map.plantGrass(new Grass(new Vector2D(50,50)));
        map.simulateOneDay();
        assertEquals(12.5, fox.getEnergy());
        assertEquals(12.5, bat.getEnergy());
//        assertNull(map.plants.get(new Vector2D(50,50)));

    }

    private int[] fillGenom(int a){
        int[] arr = new int[32];
        for(int i = 0; i < 32; i++){
            arr[i] = a;
        }
        return arr;
    }
}
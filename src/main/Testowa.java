package main;

import jdk.jfr.StackTrace;

import java.awt.EventQueue;

public class Testowa {
    public static void main(String[] args) {
        WholeMap map = new WholeMap(50,50, 5, 0.5);
        int[] genomTest = new int[32];
        for(int i = 0; i < 32; i++) genomTest[i] = 0;

        //map.placeAnimal(new Animal(map,new Vector2D(1,1),new Orientation(0),new Genom(new int[32]), 15.0));
        //map.placeAnimal(new Animal(map,new Vector2D(3,1),new Orientation(0),new Genom(new int[32]), 15.0));
        map.placeAnimal(new Animal(map,new Vector2D(5,1),new Orientation(0),Genom.getRandomGenom(), 180.0));
        map.plantGrass(new Grass(new Vector2D(5,3)));
        map.plantGrass(new Grass(new Vector2D(15,3)));
        map.plantGrass(new Grass(new Vector2D(25,3)));
        MyFrame frame = new MyFrame(map);
    }

}
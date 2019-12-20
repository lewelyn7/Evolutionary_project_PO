package main;

import jdk.jfr.StackTrace;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.json.*;
public class Testowa {
    public static void main(String[] args) {

        File jsonInputFile = new File("/home/karolh/Desktop/studia/semestr3/PO/proj1_generator_ewolucyjny/src/main/params.json");
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
            // Get the JsonObject structure from JsonReader.
            JsonObject empObj = reader.readObject();
            reader.close();
            // read string data




        WholeMap map = new WholeMap(
                empObj.getInt("width"),
                empObj.getInt("height"),
                Double.parseDouble(empObj.getString("plantEnergy")),
                Double.parseDouble(empObj.getString("jungleRatio")),
                Double.parseDouble(empObj.getString("startEnergy"))*0.5);
        int[] genomTest = new int[32];
        int[] genomTest2 = new int[32];
        for(int i = 0; i < 32; i++) genomTest[i] = 0;
        for(int i = 0; i < 32; i++) genomTest2[i] = 0;

        //map.placeAnimal(new Animal(map,new Vector2D(1,1),new Orientation(0),new Genom(new int[32]), 15.0));
        //map.placeAnimal(new Animal(map,new Vector2D(3,1),new Orientation(0),new Genom(new int[32]), 15.0));
//        map.placeAnimal(new Animal(map,new Vector2D(5,5),new Orientation(0),new Genom(genomTest), 50.0));
//        map.placeAnimal(new Animal(map,new Vector2D(5,21),new Orientation(4),new Genom(genomTest), 50.0));
        map.placeAnimal(new Animal(map,new Vector2D(20,35),new Orientation(0),Genom.getRandomGenom(), 40.0));
        map.placeAnimal(new Animal(map,new Vector2D(20,35),new Orientation(0),Genom.getRandomGenom(), 40.0));
        map.placeAnimal(new Animal(map,new Vector2D(20,45),new Orientation(0),Genom.getRandomGenom(), 40.0));
        map.placeAnimal(new Animal(map,new Vector2D(20,32),new Orientation(0),Genom.getRandomGenom(), 40.0));
        map.placeAnimal(new Animal(map,new Vector2D(25,40),new Orientation(0),Genom.getRandomGenom(), 40.0));
        map.placeAnimal(new Animal(map,new Vector2D(26,40),new Orientation(0),Genom.getRandomGenom(), 40.0));
        map.placeAnimal(new Animal(map,new Vector2D(28,40),new Orientation(0),Genom.getRandomGenom(), 40.0));
        map.placeAnimal(new Animal(map,new Vector2D(29,40),new Orientation(0),Genom.getRandomGenom(), 40.0));
        map.placeAnimal(new Animal(map,new Vector2D(30,40),new Orientation(0),Genom.getRandomGenom(), 40.0));
        map.plantGrass(new Grass(new Vector2D(5,20)));
        map.plantGrass(new Grass(new Vector2D(5,15)));
//        map.plantGrass(new Grass(new Vector2D(25,3)));
        MyFrame frame = new MyFrame(map);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("can't find params.json file");
        }
    }

}
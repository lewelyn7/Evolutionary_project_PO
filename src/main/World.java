package main;

import Visualisation.MyFrame;
import assets.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import javax.json.*;
public class World {
    public static void main(String[] args) {
        URL url = World.class.getResource("params.json");
        File jsonInputFile = new File(url.getPath());
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
            // Get the JsonObject structure from JsonReader.
            JsonObject empObj = reader.readObject();
            reader.close();
            // read string data



        double startEnergy = Double.parseDouble(empObj.getString("startEnergy"));
        WholeMap map = new WholeMap(
                empObj.getInt("width"),
                empObj.getInt("height"),
                Double.parseDouble(empObj.getString("plantEnergy")),
                Double.parseDouble(empObj.getString("jungleRatio")),
                startEnergy*0.5);

        for(int i = 0; i < 18; i++){
            map.placeAnimal(Animal.generateRandomAnimal(map, startEnergy));
        }

        int[]    arr = new int[32];
        for(int i = 0; i < 32; i++) arr[i] = 0;
        map.placeAnimal(new Animal(map, new Vector2D(10,10),new Orientation(0), new Genom(arr), 350));
        MyFrame frame = new MyFrame(map, 10);

        } catch (FileNotFoundException e) {
            System.out.println("can't find params.json file");
        }
    }

}
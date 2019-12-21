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

        for(int i = 0; i < 30; i++){
            map.placeAnimal(Animal.generateRandomAnimal(map, startEnergy));
        }

        for(int i = 0; i < 25; i++){
            map.plantGrassRandomly();
            map.plantJungleGrassRandomly();
        }

        MyFrame frame = new MyFrame(map, 10);

        } catch (FileNotFoundException e) {
            System.out.println("can't find params.json file");
        }
    }

}
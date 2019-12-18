package main;

import java.awt.*;
import java.awt.geom.*;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
    WholeMap map;
    private int objectSize = 10;
    public MyPanel(WholeMap map) {
        this.map = map;
        setPreferredSize(new Dimension(map.xSize*objectSize, map.ySize*objectSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(180,200,180));
        g.fillRect(0,0,map.xSize*objectSize,map.ySize*objectSize);
        g.setColor(new Color(150,220,150));
        g.fillRect(map.jungle.bottomLeft.x*objectSize, map.jungle.bottomLeft.y*objectSize, map.jungle.xSize*objectSize, map.jungle.ySize*objectSize);

        drawAnimals(g2d,map);
        drawGrasses(g2d, map);

    }
    void drawAnimals(Graphics2D g, WholeMap map){

        for(Animal s : map.animalsList){
            drawAnimal(g, s);
        }
    }
    void drawAnimal(Graphics2D g, Animal animal){
        g.setColor(new Color(10,10,10));
        g.fillRect(animal.getPosition().x*objectSize, animal.getPosition().y*objectSize, objectSize,objectSize);
    }

    void drawGrass(Graphics2D g, Grass grass){
        g.setColor(new Color(10,200,10));
        g.fillRect(grass.getPosition().x*objectSize, grass.getPosition().y*objectSize,objectSize, objectSize);
    }

    void drawGrasses(Graphics2D g, WholeMap map){
        for(Map.Entry<Vector2D, Grass> s: map.plants.entrySet()){
            drawGrass(g, s.getValue());
        }
    }

}
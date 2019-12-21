package Visualisation;

import assets.Animal;
import assets.Grass;
import assets.Vector2D;
import assets.WholeMap;

import java.awt.*;
import java.util.Map;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
    WholeMap map;
    private int objSize;
    public MyPanel(WholeMap map, int objSize) {
        this.objSize = objSize;
        this.map = map;
        setPreferredSize(new Dimension(map.xSize* this.objSize, map.ySize* this.objSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(180,200,180));
        g.fillRect(0,0,map.xSize* objSize,map.ySize* objSize);
        g.setColor(new Color(150,220,150));
        g.fillRect(map.jungle.bottomLeft.x* objSize, map.jungle.bottomLeft.y* objSize, map.jungle.xSize* objSize, map.jungle.ySize* objSize);
        drawGrasses(g2d, map);

        drawAnimals(g2d,map);

    }
    void drawAnimals(Graphics2D g, WholeMap map){

        for(Animal s : map.animalsList){
            drawAnimal(g, s);
        }
    }
    void drawAnimal(Graphics2D g, Animal animal){
        int redComponent = (((int)(animal.getEnergy()*120/map.procreationEnergy)));
        if(redComponent > 255) redComponent = 255;
        g.setColor(new Color(redComponent,10,10));
        g.fillRect(animal.getPosition().x* objSize, animal.getPosition().y* objSize, objSize, objSize);
    }

    void drawGrass(Graphics2D g, Grass grass){
        g.setColor(new Color(10,200,10));
        g.fillRect(grass.getPosition().x* objSize, grass.getPosition().y* objSize, objSize, objSize);
    }

    void drawGrasses(Graphics2D g, WholeMap map){
        for(Map.Entry<Vector2D, Grass> s: map.plants.entrySet()){
            drawGrass(g, s.getValue());
        }
    }

}
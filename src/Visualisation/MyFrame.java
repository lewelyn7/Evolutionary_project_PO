package Visualisation;

import assets.Simulation;
import assets.WholeMap;

import javax.swing.*;
import java.awt.*;


public class MyFrame extends JFrame {
    WholeMap map;
    int objSize;
    public MyFrame(WholeMap map, int objSize){
        super("Darwin's World");
        this.objSize = objSize;
        this.map = map;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(map.xSize*objSize+20,map.ySize*objSize+50);
        setLocation(50,50);
        setLayout(new FlowLayout());
        JPanel mapPanel = new MyPanel(map, objSize);
        Simulation simulation = new Simulation(mapPanel, map, 500);
        setVisible(true);
        add(mapPanel);
        simulation.start();
    }
}

package main;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;


public class MyFrame extends JFrame {
    WholeMap map;
    public MyFrame(WholeMap map){
        super("Hello World");
        this.map = map;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setLocation(50,50);

        JPanel panel = new MyPanel(map);
        Simulation simulation = new Simulation(panel, map, 500);
        setVisible(true);
        add(panel);
        simulation.start();
    }
}

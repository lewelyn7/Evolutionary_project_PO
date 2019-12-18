package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Simulation implements ActionListener {
    private Timer timer;
    private JPanel panel;
    private WholeMap map;
    private int delay;

    public Simulation(JPanel panel, WholeMap map, int delay) {
        timer = new Timer(delay, this);
        this.panel = panel;
        this.map = map;
        this.delay = delay;
    }

    public void start(){
        // rzczy startowe TODO

        timer.start();


    }

    @Override
    public void actionPerformed(ActionEvent a){
        //things to simulate;
       // map.simulateOneDay();
        for(Animal s: map.animalsList){
            s.moveRandomly();
            System.out.println(s.toString());
        }

        panel.repaint();
    }

}

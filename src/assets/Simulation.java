package assets;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Simulation implements ActionListener {
    private Timer timer;
    private JPanel panel;
    private WholeMap map;

    public Simulation(JPanel panel, WholeMap map, int delay) {
        timer = new Timer(delay, this);
        this.panel = panel;
        this.map = map;
    }

    public void start(){
        // rzczy startowe TODO

        timer.start();


    }

    @Override
    public void actionPerformed(ActionEvent a){
        //things to simulate;
        map.simulateOneDay();

        panel.repaint();
    }

}

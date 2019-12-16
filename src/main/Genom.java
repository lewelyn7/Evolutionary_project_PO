package main;

import java.util.Random;

public class Genom {
    int[] gens = new int[32];
    Random generator = new Random();

    public int getRandomGen(){
        return generator.nextInt(32);
    }
}

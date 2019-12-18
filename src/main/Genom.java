package main;

import java.util.Random;

public class Genom {


    int[] gens = new int[32];
    Random generator = new Random();

    public int getRandomGen(){
        return gens[generator.nextInt(32)];
    }
    public Genom(int[] gens) {
        this.gens = gens;
    }

    public static Genom getRandomGenom(){
        Random generator = new Random();
        int[] randArr = new int[32];
        for(int i = 0; i < 32; i++){
            randArr[i] = generator.nextInt(8);
        }
        return new Genom(randArr);
    }
}

package assets;

import java.util.Arrays;
import java.util.Random;

public class Genom {


    int[] gens;
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

    public Genom mixGenoms(Genom other){
        Random divisionPointGenerator = new Random();
        int[] newGenomArr = new int[32];
        int[] gensQuantity = new int [8];

        for(int i = 0; i < 8; i++) gensQuantity[i]  = 0;

        int divPointA = divisionPointGenerator.nextInt(32);
        int divPointB = divisionPointGenerator.nextInt(32);
        if(divPointA > divPointB){
            int temp;
            temp = divPointA;
            divPointA = divPointB;
            divPointB = temp;
        }

        for(int i = 0; i < 32; i++){
            if(i < divPointA){
                newGenomArr[i] = this.gens[i];
                gensQuantity[newGenomArr[i]]++;
            } else if(i <= divPointB) {
                newGenomArr[i] = other.gens[i];
                gensQuantity[newGenomArr[i]]++;
            }else {
                newGenomArr[i] = this.gens[i];
                gensQuantity[newGenomArr[i]]++;
            }
        }

        for(int i = 0; i < 8; i++){
            if(gensQuantity[i] == 0){
                int j = 0;
                while(gensQuantity[newGenomArr[j]] <= 1){
                    j++;
                }
                gensQuantity[newGenomArr[j]]--;
                newGenomArr[j] = i;
                gensQuantity[i]++;
            }
        }
        Arrays.sort(newGenomArr);
        return new Genom(newGenomArr);

    }

    @Override
    public String toString(){
        return Arrays.toString(gens);
    }
}

package main;


public class WholeMap {
    public int xSize;
    public int ySize;
    public float jungleRatio;
    MultiMap<Vector2D, Iorganism> organisms = new MultiMap<>();


    public void WholeMap(int xSize, int ySize){
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public boolean place(Iorganism organism){
        organisms.putOne(organism.getPosition);
    }



}

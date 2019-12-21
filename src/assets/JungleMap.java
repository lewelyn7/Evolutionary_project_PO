package assets;

import java.util.Random;

public class JungleMap {
    public final Vector2D bottomLeft;
    public final Vector2D upperRight;

    public final int xSize;
    public final int ySize;

    public int objCounter = 0;
    public int capacity;
    private Random xGenerator = new Random();
    private Random yGenerator = new Random();

    public JungleMap(Vector2D bottomLeft, Vector2D upperRight, int xSize, int ySize) {
        this.bottomLeft = bottomLeft;
        this.upperRight = upperRight;
        this.xSize = xSize;
        this.ySize = ySize;
        this.capacity = xSize*ySize;
    }

    public boolean contains(Vector2D vect){
        return (vect.precedes(upperRight) && vect.follows(bottomLeft));
    }

    public Vector2D getRandomVect(){
        return new Vector2D(xGenerator.nextInt(this.xSize)+this.bottomLeft.x, yGenerator.nextInt(this.ySize)+this.bottomLeft.y);
    }
}

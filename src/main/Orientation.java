package main;

public class Orientation {

    private int value;

    public Orientation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value){
        if(value > 7 || value < 0){
            throw new IllegalArgumentException("cant set " + value + "orientation");
        }
        this.value = value;
    }

    public void addValue(int value){
        this.value = (this.value + value)%8;
    }

    public Vector2D getVector(){
        switch (value){
            case 0:
                return new Vector2D(0,0);

            case 1:
                return new Vector2D(1,1);

            case 2:
                return new Vector2D(1,0);

            case 3:
                return new Vector2D(1,-1);

            case 4:
                return new Vector2D(0,-1);

            case 5:
                return new Vector2D(-1,-1);

            case 6:
                return new Vector2D(-1,0);

            case 7:
                return new Vector2D(-1,1);

            default:
                return null;

        }
    }


}

package main;

public class Orientation {

    private int value;

    public Orientation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) throws IllegalAccessException {
        if(value > 7 || value < 0){
            throw new IllegalAccessException("cant set " + value + "orientation");
        }
        this.value = value;
    }


}

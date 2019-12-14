package main;
public class Vector2D {
    public final int x;
    public final int y;

    public Vector2D(int tempx, int tempy) {
        this.x = tempx;
        this.y = tempy;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("(");
        result.append(x);
        result.append(",");
        result.append(y);
        result.append(")");
        //System.out.println(result);
        return result.toString();
    }
    public boolean precedes(Vector2D other){
        return this.x <= other.x && this.y <= other.y;
    }
    public boolean follows(Vector2D other){
        return this.x >= other.x && this.y >= other.y;
    }
    public Vector2D upperRight(Vector2D other){
        int tempx, tempy;
        if(this.x > other.x) {
            tempx = this.x;
        }else{
            tempx = other.x;
        }
        if(this.y > other.y) {
            tempy = this.y;
        }else{
            tempy = other.y;
        }
        return new Vector2D(tempx,tempy);
    }
    public Vector2D lowerLeft(Vector2D other){
        int tempx, tempy;
        if(this.x > other.x) {
            tempx = other.x;
        }else{
            tempx = this.x;
        }
        if(this.y > other.y) {
            tempy = other.y;
        }else{
            tempy = this.y;
        }
        return new Vector2D(tempx,tempy);
    }
    public Vector2D add(Vector2D other){
        return new Vector2D(this.x+other.x,this.y+other.y);
    }
    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x-other.x,this.y-other.y);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null || other.getClass() != this.getClass())
            return false;
        Vector2D o = (Vector2D) other;
        return this.x == o.x && this.y == o.y;
    }
    public Vector2D opposite(){
        return new Vector2D(-this.x,-this.y);
    }
    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
}

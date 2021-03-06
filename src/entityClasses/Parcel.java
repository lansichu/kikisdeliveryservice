package entityClasses;

/**
 * Created by stellafang. on 2016-03-26.
 */
public class Parcel {
    private int pID;
    private float length;
    private float width;
    private float height;
    private float weight;
    private int dID;
    private String cID;
    private String next_cID;

    public Parcel() { }

    public int getpID() { return pID; }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public float getLength() { return length; }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() { return width; }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() { return height; }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getdID() {
        return dID;
    }

    public void setdID(int dID) {
        this.dID = dID;
    }

    public String getcID() {return cID;}

    public void setcID(String cID) {this.cID = cID;}

    public String getNextcID() {return next_cID;}

    public void setNextcID(String next_cID) {this.next_cID = next_cID;}

}

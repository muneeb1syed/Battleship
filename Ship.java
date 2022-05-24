public class Ship {
    private int size, health;
    private String name;
    private int[] shipLocations;

    public Ship(String name, int size) {
        this.size = size;
        health = size;
        this.name = name;
        shipLocations = new int[size];
    }

    public void setDefaultHealth() {
        health = size;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public void setShipLocations(int index, int value) {
        shipLocations[index] = (byte)value;
    }

    public int[] getShipLocations() {
        return shipLocations;
    }

    public void takeHit() {
        health--;
    }

    public boolean isDestroyed() {
        if(health == 0) return true;
        return false;
    }
}
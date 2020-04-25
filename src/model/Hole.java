package model;

public class Hole {
    private int location;
    private int size;
    private int order;
    private boolean available;

    public Hole(int location, int size, boolean available) {
        this.location = location;
        this.size = size;
        this.available = available;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

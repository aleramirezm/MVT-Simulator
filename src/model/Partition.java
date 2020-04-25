package model;

public class Partition{
    private int location;
    private int size;
    private boolean available;
    private Process process;

    public Partition(int location, int size, boolean available, Process process) {
        this.location = location;
        this.size = size;
        this.available = available;
        this.process = process;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
}

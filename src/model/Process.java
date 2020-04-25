package model;

public class Process {
    private String name;
    private int size;
    private int arrivalTime;
    private int finishTime;
    private int index;
    private int dur;
    private boolean active;

    public Process(String name, int size, int arrivalTime, int finishTime, int index, int dur) {
        this.name = name;
        this.size = size;
        this.arrivalTime = arrivalTime;
        this.finishTime = finishTime;
        this.index = index;
        this.dur = dur;
        this.active = false;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDur() {
        return dur;
    }

    public void setDur(int dur) {
        this.dur = dur;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

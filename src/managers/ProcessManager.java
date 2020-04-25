package managers;

import java.util.ArrayList;
import java.util.List;

import model.Process;

public class ProcessManager {
    private int size;
    private List<Process> list;

    public ProcessManager() {
        this.size=0;
        list = new ArrayList<>();
    }

    public void createProcess(String name, int time, int dur, int processSize){
        Process process = new Process(name, processSize, time, dur+time, size, dur);
        list.add(process);
        this.size++;
        System.out.println(name+" has been created with "+time+" arrival time "+dur+" duration "+processSize+" size");
    }
    public void deleteProcess(int index){
        list.remove(index);
        this.size--;
    }

    public int getSize() {
        return size;
    }

    public Process getProcess(int index){
        return list.get(index);
    }

    public List<Process> getList() {
        return list;
    }
}

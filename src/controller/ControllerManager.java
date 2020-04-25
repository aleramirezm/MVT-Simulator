package controller;

import managers.ProcessManager;
import managers.OperatingSystem;
import model.Partition;
import model.Process;
import model.Hole;

import java.util.List;

public class ControllerManager {
    private ProcessManager p;
    private OperatingSystem os;

    public ControllerManager() {
        p = new ProcessManager();
        os = new OperatingSystem();
    }

    public void create(String name, int time, int dur, int size){
        p.createProcess(name, time, dur, size);
    }

    public void startOS(int totalSize, int osSize){
        os.startOS(totalSize, osSize);
    }
    public boolean setTime(){
        if (p.getSize() == 0) return false;
        os.setTimer(os.getTimer() + 1);
        os.setI(os.getI() + 1);
        os.startLoop(p);
        return true;
    }
    public int getTimer(){
        return os.getTimer();
    }
    public int getProcessesSize(){
        return p.getSize();
    }
    public int getOSSize(){
        return os.getSize();
    }
    public int getOSHoleSize(){
        return os.getSizeFirstHole();
    }

    public Process getAProcess(int index){
        return p.getProcess(index);
    }

    public Hole getHole(int index){
        return os.getMemoryManager().getAvailable().get(index);
    }
    public List<Hole> getHoleList(){return os.getMemoryManager().getAvailable();}

    public int getHoleSize(){
        return os.getMemoryManager().getAvailable().size();
    }

    public Partition getAPartition(int index){
        return os.getMemoryManager().getTablePartition().get(index);
    }
    public int getPartSize(){
        return os.getMemoryManager().getTablePartition().size();
    }
    public List<Partition> getPartList(){return os.getMemoryManager().getTablePartition();}

    public List<Process> getProcessList(){
        return p.getList();
    }




}

package managers;


import model.*;
import model.Process;

import java.util.ArrayList;
import java.util.List;

public class MemoryManager {
    private List<Hole> available;
    private List<Partition> tablePartition;

    public MemoryManager(int localization, int size) {
        this.available = new ArrayList<>();
        this.tablePartition = new ArrayList<>();
        Hole hole = new Hole(localization, size, true);
        available.add(hole);
    }
    public boolean assign(Process p){
        for(Hole holes: available){
            if(holes.getSize() >= p.getSize() && holes.isAvailable()){
                System.out.println("Añadiendo " + p.getName());
                tablePartition.add(new Partition(holes.getLocation(), p.getSize(),false, p));
                holes.setLocation(holes.getLocation()+p.getSize());
                holes.setSize(holes.getSize()-p.getSize());
                return false;
            }
        }
        return true;

    }

    public List<Hole> getAvailable() {
        return available;
    }

    public List<Partition> getTablePartition() {
        return tablePartition;
    }

    public void showMemory(){
        //solo debe imprimir los datos que estén almacenados en las listas
        for(Hole availables: available){
            System.out.println(availables.getLocation());
            System.out.println(availables.getSize());
            System.out.println(availables.isAvailable());
            System.out.println("\n\n");
        }

        for(Partition partitions: tablePartition){
            System.out.println(partitions.getProcess().getIndex());
            System.out.println(partitions.getLocation());
            System.out.println(partitions.getSize());
            System.out.println(partitions.getProcess().getName());
            System.out.println("\n\n");
        }
        System.out.println("\n\n");
    }

    public void delete(int index){
        System.out.println("Eliminando " + tablePartition.get(index).getProcess().getName());
        int localization = tablePartition.get(index).getLocation();
        int size = tablePartition.get(index).getSize();
        tablePartition.get(index).setAvailable(true);
        Hole hole = new Hole(localization, size, true);
        available.add(hole);
        tablePartition.remove(index);
    }

}

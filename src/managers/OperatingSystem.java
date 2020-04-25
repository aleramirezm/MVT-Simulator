package managers;

public class OperatingSystem {
    private int timer;
    private int i;
    private int sizeFirstHole;
    private MemoryManager memory;
    private int size;

    public OperatingSystem() {
        this.timer = 0;
        this.i = -1;
    }
    public void startOS(int totalSize, int sizeOS){
        size = sizeOS;
        sizeFirstHole = totalSize - sizeOS;
        memory = new MemoryManager(sizeOS, this.sizeFirstHole);
        System.out.println("sizeOS: "+sizeOS+" totalSize: "+totalSize+" sizeFirstHole: "+sizeFirstHole+" Timer: "+this.timer);
    }

    public void startLoop(ProcessManager pManager){
        if(this.i<=pManager.getSize()){
            if(this.i == pManager.getSize()){
                System.out.println("Reiniciando el ciclo");
                setI(0);
            }

            for(int j = 0; j < pManager.getSize(); j++){
                if(pManager.getProcess(j).getFinishTime() == this.timer){
                    System.out.println("Tiempo exacto (delete)" + timer);
                    memory.delete(j);
                    pManager.deleteProcess(j);
                }else if(pManager.getProcess(j).getArrivalTime() == this.timer ){
                    System.out.println("Tiempo exacto (assign)" + this.timer);
                    pManager.getProcess(j).setActive(memory.assign(pManager.getProcess(j)));
                } else if(pManager.getProcess(j).getArrivalTime() < this.timer && pManager.getProcess(j).isActive()){
                    System.out.println("Tiempo exacto (waitinglist)" + this.timer);
                    pManager.getProcess(j).setActive(memory.assign(pManager.getProcess(j)));
                    pManager.getProcess(j).setFinishTime(pManager.getProcess(j).getDur()+this.timer);
                }
            }
            System.out.println("Tiempo exacto (out)" + this.timer);
            memory.showMemory();
        } else{
            System.out.println("Tiempo exacto (else) " + (this.timer+1));
            if(timer == pManager.getProcess(0).getFinishTime()){
                memory.delete(0);
                pManager.deleteProcess(0);
            }
            memory.showMemory();
        }

    }

    public MemoryManager getMemoryManager(){
        return memory;
    }


    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getSize(){
        return size;
    }
    public int getSizeFirstHole(){
        return sizeFirstHole;
    }
}

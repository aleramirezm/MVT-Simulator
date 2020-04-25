package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Process;
import model.Hole;
import model.Partition;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private ControllerManager logic = new ControllerManager();
    private Alerts alert = new Alerts();
    @FXML private AnchorPane input; @FXML private TabPane tabPane;
    @FXML private TextField processInput; @FXML private TextField time; @FXML private TextField dur;
    @FXML private TextField sizeInput; @FXML private TextField totalSize; @FXML private TextField osSize;
    @FXML private Button button; @FXML private PieChart pieChart; @FXML private TextArea textArea;
    @FXML private TableView<Process> processTable; @FXML private TableColumn<Process, String> processId;
    @FXML private TableColumn<Process, String> processSize; @FXML private TableColumn<Process, String> processArrival;
    @FXML private TableColumn<Process, String> processDur; @FXML private TextField timer;
    @FXML private TableView<Hole> availableAreas; @FXML private TableColumn<Hole, String> holeLoc;
    @FXML private TableColumn<Hole, String> holeSize; @FXML private TableColumn<Hole, String> holeStatus;
    @FXML private TableView<Partition> partitions; @FXML private TableColumn<Partition, String> partLoc;
    @FXML private TableColumn<Partition, String> partSize; @FXML private TableColumn<Partition, String> partStatus;




    public void onSaveAll(MouseEvent event){
        input.setVisible(false);
        tabPane.setVisible(true);
        if(!isEmpty(2)) {
            int totalS = Integer.parseInt(totalSize.getText());
            int os = Integer.parseInt(osSize.getText());
            if(totalS>os){
                logic.startOS(totalS, os);
            }else alert.showAlert("Total size can't be smaller than Operating System's size");
        }else{
            alert.showAlert("OS size or total size aren't defined");
        }

        loadData();
        loadProcessTable();
    }

    public void onSaveProcess(MouseEvent event){
        if(!isEmpty(1)) {
            String name = processInput.getText();
            int arrival = Integer.parseInt(time.getText());
            int durTime = Integer.parseInt(dur.getText());
            int size = Integer.parseInt(sizeInput.getText());
            logic.create(name, arrival, durTime, size);
            alert.showInfo(name+" has been created");
        }else alert.showAlert("Please, fill all fields.");
        processInput.clear();
        time.clear();
        dur.clear();
        sizeInput.clear();
    }

    public void onNextButton(MouseEvent e){
        boolean flag = logic.setTime();
        if(!flag){
            button.setDisable(true);
        }
        timer();
        loadData();
        loadInfo();
        loadTableData();
    }

    private void loadData(){
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        list.add(new PieChart.Data("Operating System",logic.getOSSize()));
        list.add(new PieChart.Data("Hole", logic.getOSHoleSize()));
        if(logic.getProcessesSize()!=0) {
            for (int i = 0; i < logic.getProcessesSize(); i++) {
                System.out.println("adding chart "+logic.getAProcess(i).getName());
                list.add(new PieChart.Data(logic.getAProcess(i).getName(), logic.getAProcess(i).getSize()));
            }
        }
        if(logic.getHoleSize()!=0){
            for(int i= 0; i < logic.getHoleSize(); i++){
                list.add(new PieChart.Data("Hole", logic.getHole(i).getSize()));
            }
        }
        pieChart.setData(list);
    }

    private void loadInfo(){
        textArea.clear();
        textArea.setWrapText(true);
        if(logic.getPartSize()!=0) {
             textArea.setText("\n------------Partitions------------\n");
             for (int i = 0; i < logic.getPartSize(); i++) {
                 textArea.appendText("\nProcessID " + logic.getAPartition(i).getProcess().getName());
                 textArea.appendText("\nLocation " + logic.getAPartition(i).getLocation());
             }
         }
        textArea.appendText("\n------------Holes------------\n");
        for(int i = 0; i<logic.getHoleSize(); i++){
            textArea.appendText("\nHole size "+logic.getHole(i).getSize());
            textArea.appendText("\nLocation "+logic.getHole(i).getLocation());
        }
        textArea.appendText("\n------------------------");
    }

    private void timer(){
        timer.clear();
        timer.setText(String.valueOf(logic.getTimer()-1));
    }

    private boolean isEmpty(int i){
         if(i ==1) {
             return processInput.getText().isEmpty() & time.getText().isEmpty() & dur.getText().isEmpty()
                     & sizeInput.getText().isEmpty();
         }else{
             return totalSize.getText().isEmpty() & osSize.getText().isEmpty();
         }
    }

    private void loadProcessTable(){
        List<Process> processList = logic.getProcessList();
        for(Process p: processList){
            processTable.getItems().add(p);
        }
    }

    private void loadTableData(){
        List<Hole> holes = logic.getHoleList();
        List<Partition> part = logic.getPartList();
        
        partitions.getItems().clear();
        availableAreas.getItems().clear();
        for(Hole h: holes){
            availableAreas.getItems().add(h);
        }
        for(Partition p: part){
            partitions.getItems().add(p);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //processes
        processId.setCellValueFactory(new PropertyValueFactory<Process, String>("name"));
        processSize.setCellValueFactory(new PropertyValueFactory<Process, String>("size"));
        processArrival.setCellValueFactory(new PropertyValueFactory<Process, String>("arrivalTime"));
        processDur.setCellValueFactory(new PropertyValueFactory<Process, String>("dur"));

        //partitions
        partSize.setCellValueFactory(new PropertyValueFactory<Partition, String>("size"));
        partLoc.setCellValueFactory(new PropertyValueFactory<Partition, String>("location"));
        partStatus.setCellValueFactory(new PropertyValueFactory<Partition, String>("available"));

        //holes
        holeLoc.setCellValueFactory(new PropertyValueFactory<Hole, String>("location"));
        holeSize.setCellValueFactory(new PropertyValueFactory<Hole, String>("size"));
        holeStatus.setCellValueFactory(new PropertyValueFactory<Hole, String>("available"));
    }
}

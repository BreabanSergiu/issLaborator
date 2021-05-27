package controller;

import domain.Comanda;
import domain.Medicament;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import services.Observer;
import services.Services;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.*;

public class MainPersonalMController extends UnicastRemoteObject implements Observer, Serializable {

    ObservableList<Comanda> modelComanda = FXCollections.observableArrayList();
    ObservableList<Medicament> modelMedicament = FXCollections.observableArrayList();
    MainViewPersonalM mainViewPersonalM;
    private static Set<Medicament> medicamenteleUneiComenzi = new HashSet<>();
    private static final String STATUS_ORDER_DEFAULT = "nerealizata";


    public MainViewPersonalM getMainViewPersonalM() {
        return mainViewPersonalM;
    }

    public void setMainViewPersonalM(MainViewPersonalM mainViewPersonalM) {
        this.mainViewPersonalM = mainViewPersonalM;
    }

    Services services;

    @FXML
    TableView<Comanda> tabelViewListaComenzilorSpital;

    @FXML
    TableView<Medicament> tableViewMedicamenteleComenzii;

    @FXML
    TextField textFieldNumeleMedicamentului;

    
    @FXML
    Spinner<String> spinnerNivelComanda;

    @FXML
    Button buttonAdaugaMedicamentLaComanda;
    
    @FXML
    Button buttonAdaugaComanda;

    public MainPersonalMController() throws RemoteException {
    }


    @FXML
    void initialize(){
        tabelViewListaComenzilorSpital.setItems(modelComanda);
        tableViewMedicamenteleComenzii.setItems(modelMedicament);
    }

    public void setService(Services services) {
        this.services = services;
        initModel();
        initSpinner();
    }

    void initSpinner(){
        ObservableList<String> valueStrings = FXCollections.observableArrayList("normal","urgent");
        SpinnerValueFactory<String> value = new SpinnerValueFactory.ListSpinnerValueFactory<String>(valueStrings);
        spinnerNivelComanda.setValueFactory(value);
    }
    private void initModel() {
        tabelViewListaComenzilorSpital.getItems().forEach(System.out::println);
        try{
            List<Comanda> comandaList = new ArrayList<>();
            Iterable<Comanda> comandas  = services.getAllComenzi();
            comandas.forEach(comandaList::add);
            modelComanda.setAll(comandaList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fillOrderList(MouseEvent mouseEvent) {
        Set<Medicament> medicaments = tabelViewListaComenzilorSpital.getSelectionModel().getSelectedItem().getMedicamentList();
        initModelOrderList(medicaments);
    }

    private void initModelOrderList(Set<Medicament> medicaments) {
        modelMedicament.setAll(medicaments);
    }

    public void deleteOrder(MouseEvent mouseEvent) {
        Comanda selectedOrder = tabelViewListaComenzilorSpital.getSelectionModel().getSelectedItem();
        Comanda comandaDeleted = null;
        if(selectedOrder.getStatus().equals("nerealizata")){
            comandaDeleted = services.deleteOrder(selectedOrder.getId());
        }

        Alert alert;
        if(comandaDeleted != null){
            alert = new Alert(Alert.AlertType.INFORMATION, "successfully deleted");
        }else{
            alert = new Alert(Alert.AlertType.INFORMATION, "delete error");
        }
        alert.show();

    }

    @Override
    public void updateGraphicalInterface() throws RemoteException {
        Platform.runLater(this::initModel);
    }

    public void addNewOrder(MouseEvent mouseEvent) {
        String nivelulComenzii = spinnerNivelComanda.getValue();
        Comanda comanda = new Comanda(nivelulComenzii, LocalDate.now(),STATUS_ORDER_DEFAULT, medicamenteleUneiComenzi);
        try{
            services.addOrder(comanda);
            medicamenteleUneiComenzi.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully adding order!");
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addNewMedicineToOrder(MouseEvent mouseEvent) {
        String medicineName = textFieldNumeleMedicamentului.getText();
        Medicament medicament = services.getOneMedicineByName(medicineName);
        if(medicament == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "does not exist this medicine!");
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "successfully adding medicines to order!");
        alert.show();
        textFieldNumeleMedicamentului.clear();
        medicamenteleUneiComenzi.add(medicament);
       
    }
}

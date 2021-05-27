package controller;


import domain.Comanda;
import domain.Medicament;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import services.Observer;
import services.Services;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;


public class MainFarmacistController extends UnicastRemoteObject implements Observer, Serializable {

    Services services;
    MainViewFarmacist mainViewFarmacist;
    ObservableList<Comanda> modelComenziNeonorate = FXCollections.observableArrayList();
    ObservableList<Medicament> modelMedicamentePeStoc = FXCollections.observableArrayList();
    ObservableList<Medicament> modelMedicamenteleComenzii = FXCollections.observableArrayList();

    @FXML
    TableView<Comanda> tableViewListaComenzilorNeonorate;

    @FXML
    TableView<Medicament> tableViewMedicamentePeStoc;

    @FXML
    TableView<Medicament> tableViewMedicamenteleComenzii;

    @FXML
    Button buttonComandaOnorata;

    @FXML
    Button buttonComandaPartial;

    public MainFarmacistController() throws RemoteException {
    }

    public void setMainViewFarmacist(MainViewFarmacist mainViewFarmacist) {
        this.mainViewFarmacist = mainViewFarmacist;
    }

    public MainViewFarmacist getMainViewFarmacist() {
        return mainViewFarmacist;
    }

    @FXML
    void initialize(){
        tableViewListaComenzilorNeonorate.setItems(modelComenziNeonorate);
        tableViewMedicamentePeStoc.setItems(modelMedicamentePeStoc);
        tableViewMedicamenteleComenzii.setItems(modelMedicamenteleComenzii);
    }

    public void setService(Services services) {
        this.services = services;
        initModel();
    }

    private void initModel() {
        Iterable<Comanda> comandas = null;
        try{
            comandas = services.getUnfulfilledOrders();
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelComenziNeonorate.setAll((Collection<? extends Comanda>) comandas);

        Iterable<Medicament> medicaments = null;
        try{
            medicaments = services.getAllMedicinesInStock();
        } catch (Exception e) {
            e.printStackTrace();
        }

        modelMedicamentePeStoc.setAll((Collection<? extends Medicament>)medicaments);


    }


    @Override
    public void updateGraphicalInterface() throws RemoteException {
        Platform.runLater(this::initModel);
    }

    public void fillOrderList(MouseEvent mouseEvent) {
        Set<Medicament> medicaments = tableViewListaComenzilorNeonorate.getSelectionModel().getSelectedItem().getMedicamentList();
        initModelOrderList(medicaments);
    }

    private void initModelOrderList(Set<Medicament> medicaments) {
        modelMedicamenteleComenzii.setAll(medicaments);
    }

    public void handleComandaOnorata(MouseEvent mouseEvent) {
        Comanda selectedOrder = tableViewListaComenzilorNeonorate.getSelectionModel().getSelectedItem();
        services.onoreazaComanda(selectedOrder);
        modelMedicamenteleComenzii.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"comanda onorata!");
        alert.show();
    }

    public void handleComandaPartial(MouseEvent mouseEvent) {
        Comanda selectedOrder = tableViewListaComenzilorNeonorate.getSelectionModel().getSelectedItem();

        AtomicBoolean isPartial = new AtomicBoolean(false);
        selectedOrder.getMedicamentList().forEach(x->{
            Medicament medicament = services.getOneMedicineByName(x.getNumeMedicament());
            if(medicament.getCantitatePeStoc() == 0 )
            {
                isPartial.set(true);
            }
        });
        if(!isPartial.get()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Sunt toate medicamentele pe stoc, \nPoate fi o comanda onorata!");
            alert.show();
            return;
        }
        services.comandaPartiala(selectedOrder);
        modelMedicamenteleComenzii.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"comanda partial!");
        alert.show();
    }
}

package controller;


import domain.Comanda;
import domain.Medicament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import services.Services;

import java.util.Collection;


public class MainFarmacistController {

    Services services;

    ObservableList<Comanda> modelComenziNeonorate = FXCollections.observableArrayList();
    ObservableList<Medicament> modelMedicamentePeStoc = FXCollections.observableArrayList();

    @FXML
    TableView<Comanda> tableViewListaComenzilorNeonorate;

    @FXML
    TableView<Medicament> tableViewMedicamentePeStoc;

    @FXML
    void initialize(){
        tableViewListaComenzilorNeonorate.setItems(modelComenziNeonorate);
        tableViewMedicamentePeStoc.setItems(modelMedicamentePeStoc);
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


}

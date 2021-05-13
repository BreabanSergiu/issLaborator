package controller;

import domain.Comanda;
import domain.Medicament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import services.Services;

import java.util.Collection;

public class MainPersonalMController {

    ObservableList<Comanda> modelComanda = FXCollections.observableArrayList();

    Services services;

    @FXML
    TableView<Comanda> tabelViewListaComenzilorSpital;




    @FXML
    void initialize(){
        tabelViewListaComenzilorSpital.setItems(modelComanda);
    }

    public void setService(Services services) {
        this.services = services;
        initModel();
    }

    private void initModel() {
        Iterable<Comanda> comandas = null;
        try{
            comandas = services.getAllComenzi();
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelComanda.setAll((Collection<? extends Comanda>) comandas);
    }
}

package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Services;

import java.io.IOException;

public class MainViewFarmacist {


    private MainFarmacistController mainFarmacistController ;
    private Stage stage = new Stage();

    public MainViewFarmacist(Services services) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/farmacie.fxml"));

            AnchorPane root = loader.load();
            mainFarmacistController = loader.getController();
            mainFarmacistController.setService(services);
            mainFarmacistController.setMainViewFarmacist(this);
            Scene scene  = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Farmacist View");
            stage.setOnCloseRequest(event -> {
                System.exit(0);
            });

        } catch (IOException e) {
            System.out.println("in controllerul de la farmacist : "+e.getMessage());
        }
    }

    public void show(){
        stage.show();
    }

    public MainFarmacistController getMainFarmacistController(){
        return mainFarmacistController;
    }




}

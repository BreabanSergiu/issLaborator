package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Services;

import java.io.IOException;

public class MainViewFarmacist {



    public MainViewFarmacist(Services services) {
        try{
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/farmacie.fxml"));

            AnchorPane root = loader.load();
            MainFarmacistController controller = loader.getController();
            controller.setService(services);

            Scene scene  = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Farmacist View");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

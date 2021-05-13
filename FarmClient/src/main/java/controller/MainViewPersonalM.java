package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Services;

import java.io.IOException;

public class MainViewPersonalM {


    public MainViewPersonalM(Services services) {
        try{
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/spital.fxml"));

            AnchorPane root = loader.load();
            MainPersonalMController controller = loader.getController();
            controller.setService(services);

            Scene scene  = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Personal Medical View");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

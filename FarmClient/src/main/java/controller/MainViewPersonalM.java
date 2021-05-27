package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Services;

import java.io.IOException;

public class MainViewPersonalM {

    private MainPersonalMController controller;
    private Stage stage = new Stage();
    public MainViewPersonalM(Services services) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/spital.fxml"));

            AnchorPane root = loader.load();
            controller = loader.getController();
            controller.setService(services);
            controller.setMainViewPersonalM(this);
            Scene scene  = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Personal Medical View");
            stage.setOnCloseRequest(event -> {
                System.exit(0);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        stage.show();
    }

    public MainPersonalMController getMainPersonalMController(){
        return controller;
    }
}

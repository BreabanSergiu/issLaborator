package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Services;

import java.io.IOException;

public class LoginView {

    private Stage primaryStage;

    public LoginView(Stage primaryStage, Services services, MainFarmacistController mainFarmacistController, MainPersonalMController mainPersonalMController){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/login.fxml"));
            AnchorPane root = loader.load();
            LoginController loginController = loader.getController();
            this.primaryStage = primaryStage;
            loginController.setService(services,primaryStage);
            loginController.setControllers(mainFarmacistController,mainPersonalMController);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            this.primaryStage.setOnCloseRequest(event -> {
                System.exit(0);
            });
            primaryStage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showView(){
        primaryStage.show();
    }
}

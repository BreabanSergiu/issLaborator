package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Services;

import java.io.IOException;

public class LoginView {

    Stage primaryStage;

    public LoginView(Stage primaryStage, Services services){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/login.fxml"));
            AnchorPane root = loader.load();
            LoginController ctrl = loader.getController();
            this.primaryStage = primaryStage;
            ctrl.setService(services,primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showView(){
        primaryStage.show();
    }
}

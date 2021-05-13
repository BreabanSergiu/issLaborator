package controller;

import domain.Farmacist;
import domain.PersonalMedical;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.Services;


import java.io.Serializable;

public class LoginController implements Serializable{

    Services services;
    Stage primaryStage;


    @FXML
    Button buttonLoginAsNurse;

    @FXML
    Button buttonLoginAsChemist;

    @FXML
    TextField textFieldUsername;

    @FXML
    PasswordField textpasswordField;

    public void setService(Services services, Stage primaryStage) {
        this.services = services;
        this.primaryStage = primaryStage;
    }

    public void loginAsNurse(MouseEvent mouseEvent) {


        String userName = textFieldUsername.getText();
        String password = textpasswordField.getText();
        PersonalMedical personalMedical = services.loginPersonalMedical(userName,password);
        if(personalMedical != null){
            MainViewPersonalM mainViewPersonalM = new MainViewPersonalM(services);
            primaryStage.hide();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Wrong username or password");
            alert.show();
        }


    }

    public void loginAsChemist(MouseEvent mouseEvent) {

        String userName = textFieldUsername.getText();
        String password = textpasswordField.getText();
        Farmacist farmacist = services.loginFarmacist(userName,password);
        if(farmacist != null){
            MainViewFarmacist mainViewFarmacist = new MainViewFarmacist(services);
            primaryStage.hide();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Wrong username or password");
            alert.show();
        }
    }
}

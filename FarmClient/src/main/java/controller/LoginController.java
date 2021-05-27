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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginController extends UnicastRemoteObject implements Serializable {

    Services services;
    Stage primaryStage;
    MainFarmacistController mainFarmacistController;
    MainPersonalMController mainPersonalMController;

    @FXML
    Button buttonLoginAsNurse;

    @FXML
    Button buttonLoginAsChemist;

    @FXML
    TextField textFieldUsername;

    @FXML
    PasswordField textpasswordField;

    public LoginController() throws RemoteException {
    }

    public void setService(Services services, Stage primaryStage) {
        this.services = services;
        this.primaryStage = primaryStage;
    }

    public void loginAsNurse(MouseEvent mouseEvent) {
        String userName = textFieldUsername.getText();
        String password = textpasswordField.getText();
        PersonalMedical personalMedical = null;
        try{
            personalMedical = services.loginPersonalMedical(userName,password,mainPersonalMController);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,e.getMessage());
            alert.show();
        }
        if(personalMedical != null){
            mainPersonalMController.getMainViewPersonalM().show();
            primaryStage.hide();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Wrong username or password");
            alert.show();
        }
    }

    public void loginAsChemist(MouseEvent mouseEvent) {

        String userName = textFieldUsername.getText();
        String password = textpasswordField.getText();
        Farmacist farmacist = null;
        try{
            farmacist = services.loginFarmacist(userName,password,mainFarmacistController);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,e.getMessage());
            alert.show();
        }
        if(farmacist != null){
            mainFarmacistController.getMainViewFarmacist().show();
            primaryStage.hide();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Wrong username or password");
            alert.show();
        }
    }

    public void setControllers(MainFarmacistController mainFarmacistController, MainPersonalMController mainPersonalMController) {
        this.mainPersonalMController = mainPersonalMController;
        this.mainFarmacistController = mainFarmacistController;
    }
}

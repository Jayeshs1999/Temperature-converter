package com.example.myjavafxapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MyController implements Initializable
{
    @FXML
    public Label welcomeLabel;
    @FXML
    public ChoiceBox<String> choiceBox;
    @FXML
    public TextField userInputField;
    @FXML
    public Button convertButton;

    private static final String C_TO_F_TEXT="Celsius to Fahrenheit";
    private static final String F_TO_C_TEXT="Fahrenheit to Celsius";
    private Boolean isC_toF=true;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().add(C_TO_F_TEXT);
        choiceBox.getItems().add(F_TO_C_TEXT);
        choiceBox.setValue(C_TO_F_TEXT);
        
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.equals(C_TO_F_TEXT)){
                isC_toF=true;
            }else{
                isC_toF=false;
            }
        });
        
        convertButton.setOnAction(actionEvent -> {
            convert();
        
        });

    }
    private void convert() {
        String input=userInputField.getText(); //23.4=>"23.4"
        float enteredTemperature=0.0f;
        try{
            if(input.isEmpty()){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Occurred");
                alert.setHeaderText("Invalid ");
                alert.setContentText("Please Enter a temperature");
                alert.show();
                return;
            }
             enteredTemperature=Float.parseFloat(input);
        }catch (Exception e){
            warnUser();
            return;
        }

        float newTemperature=0.0f;
        if (isC_toF){
            newTemperature=(enteredTemperature*9/5)+32;
        }else{
            newTemperature=(enteredTemperature-32)*5/9;
        }
        display(newTemperature);
    }
    private void warnUser() {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occurred");
        alert.setHeaderText("Invalid Temperature Entered");
        alert.setContentText("Please Enter a valid temperature");
        alert.show();

    }

    private void display(float newTemperature) {
        String unit=isC_toF?"F":"C";
        System.out.println("The new Temperature is :"+newTemperature+""+unit);

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("The new Temperature is :"+newTemperature+""+unit);
        alert.show();
    }


}

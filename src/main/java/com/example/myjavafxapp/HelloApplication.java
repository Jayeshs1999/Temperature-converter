package com.example.myjavafxapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        System.out.println("in main method");
        launch();
    }
    @Override
    public void init() throws Exception {
        System.out.println("init method");
        super.init();
    }
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("Start method called");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        VBox rootNode=fxmlLoader.load();
        MenuBar menuBar=createMenu();
        //rootNode.getChildren().addAll(menuBar);
        rootNode.getChildren().add(0,menuBar);
        Scene scene = new Scene(rootNode);
        stage.setTitle("Temperature Converter Tool");
        stage.setScene(scene);
        stage.show();
    }
    private MenuBar createMenu(){
        Menu fileMenu=new Menu("File");
        MenuItem newMenuItem=new MenuItem("New");
        newMenuItem.setOnAction(actionEvent -> System.out.println("New Menu Item is clicked"));

        SeparatorMenuItem separatorMenuItem=new SeparatorMenuItem();
        MenuItem quiteMenuItem=new MenuItem("Quite");
        quiteMenuItem.setOnAction(event->{
            Platform.exit();
            System.exit(0);
        });

        fileMenu.getItems().addAll(newMenuItem,separatorMenuItem,quiteMenuItem);
        Menu helpMenu=new Menu("help");
        MenuItem aboutMenuItem=new MenuItem("About");
        aboutMenuItem.setOnAction(event->aboutApp());

        helpMenu.getItems().addAll(aboutMenuItem);
        MenuBar menuBar=new MenuBar();
        menuBar.getMenus().addAll(fileMenu,helpMenu);
        return menuBar;
    }

    private void aboutApp() {
        Alert alertDialogue=new Alert(Alert.AlertType.INFORMATION);
        alertDialogue.setTitle("My First Desktop application");
        alertDialogue.setHeaderText("Learning JavaFX");
        alertDialogue.setContentText("I am just a beginner but soon i will be pro  and start developing awesome java Game ");
        ButtonType yesBtn=new ButtonType("Yes");
        ButtonType noBtn=new ButtonType("No");
        alertDialogue.getButtonTypes().setAll(yesBtn,noBtn);

        Optional<ButtonType> clickBtn=alertDialogue.showAndWait();

        if(clickBtn.isPresent() && clickBtn.get()==yesBtn) {
            System.out.println("Yes Button Clicked");
        } else {
            System.out.println("No Button Clicked");
        }
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop method called");
        super.stop();
    }
}
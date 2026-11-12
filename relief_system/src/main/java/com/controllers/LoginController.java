package com.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import com.hurricane.App;

public class LoginController {

    @FXML 
    private TextField txt_password;

    @FXML 
    private TextField txt_username;

    @FXML 
    void back (ActionEvent event) throws IOException {
        App.setRoot("home");
    }
    @FXML
    void login (ActionEvent event) {
        String username = txt_username.getText();
        String password = txt_password.getText();
        System.out.println("Your name is: " + username + " and your password is: " + password);
}
}

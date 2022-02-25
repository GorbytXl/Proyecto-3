/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 *
 */
public class mostrarViewController implements Initializable {

    @FXML
    private AnchorPane panelHeroes;

    @FXML
    private Button btnConfig;

    @FXML
    private Button btnRegresar;

    @FXML
    private Label lblHeroe;
    
    @FXML
    private Label lblDescripcion;
    
    @FXML
    private Label lblEditorial;
    
    @FXML
    private Label lblAlterEgo;
    
    @FXML
    private Label lblFecha;
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtEditorial;
    
    @FXML
    private TextField txtAlterEgo;
    
    @FXML
    private TextField txtFecha;

   
    private heroeViewController controllerHeroe;
    private Stage info_heroe;
    
    public void init(String text, Stage stage, heroeViewController controllerHeroe) {
        txtNombre.setText(text);
        this.controllerHeroe = controllerHeroe;
        this.info_heroe = stage;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}

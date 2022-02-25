/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;


import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;


import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import db.mysqlconnect;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * FXML Controller class
 *
 * @author Adrian Triviño
 */
public class heroeViewController implements Initializable {

    // Rtiquetas y botones del formulario de registro 
    
    @FXML
    private AnchorPane panePrincipal;

    @FXML
    private AnchorPane paneInicio;

    @FXML
    private Button btnMarvel;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnDc;

    @FXML
    private AnchorPane paneDc;
    
    @FXML
    private AnchorPane paneMarvel;

    @FXML
    private AnchorPane paneBus;

    @FXML
    private TextField txtNombreHeroe;

    @FXML
    private ComboBox editores;

    @FXML
    private Button btnBus;
    
    // Heroes DC
    @FXML 
    private Button btnSuperman;
    
    @FXML 
    private Button btnBatman;
    
    @FXML 
    private Button btnGreen;
    
    @FXML
    private Button btnFlash;
    
    @FXML
    private Button btnAquaman;

    // Heroes Marvel
    @FXML
    private Button btnSpiderman;
    
    @FXML
    private Button btnHulk;
    
    @FXML
    private Button btnIroman;
    
    @FXML
    private Button btnThor;
    
    @FXML
    private Button btnCapitan;
    
    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtedi;

    @FXML
    private TextField txtaleg;

    @FXML
    private TextField txtPri;

    // Coneccion con el formulario sobre la
    // información de los heroes
    private Stage heroeController;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    Stage a = new Stage();

    public void Dc() {
        paneInicio.setVisible(false);
        paneBus.setVisible(false);
        paneMarvel.setVisible(false);
        paneDc.setVisible(true);
    }

    public void bBuscar() {
        paneInicio.setVisible(false);
        paneDc.setVisible(false);
        paneMarvel.setVisible(false);
        paneBus.setVisible(true);
    }
    
    public void Marvel() {
        paneInicio.setVisible(false);
        paneBus.setVisible(false);
        paneDc.setVisible(false);
        paneMarvel.setVisible(true);
    }

    @FXML
    private void buscar(ActionEvent event) throws Exception {
        conn = mysqlconnect.ConnectDB();
        try {
            pst = conn.prepareStatement("SELECT*FROM heroes WHERE editor = ? and nom_sup = ? ");
            pst.setString(1, editores.getValue().toString());
            pst.setString(2, txtNombreHeroe.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                pst = conn.prepareStatement("SELECT  nom_sup  WHERE heroes = ");
                txtedi.setText(rs.getString(1));
                txtnom.setText(rs.getString(2));
                txtaleg.setText(rs.getString(3));
                txtPri.setText(rs.getString(4));

            } else {
                JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTO ");

            }

        } catch (Exception e) {
            System.err.print(e);

        }

    } 
    
    @FXML
    private void mostrarHeroe(ActionEvent event) throws IOException {
        Object evt = event.getSource();
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/mostrarHeroeFxml.fxml"));
        Parent root = loader.load();
        mostrarViewController mostrarHeroeController = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        
        // Heroes DC
        if (evt.equals(btnSuperman)) {
            mostrarHeroeController.init(btnSuperman.getId(), stage, this);
        }else if (evt.equals(btnBatman)) {
            mostrarHeroeController.init(btnBatman.getId(), stage, this);
        }else if (evt.equals(btnGreen)) {
            mostrarHeroeController.init(btnGreen.getId(), stage, this);
        }else if (evt.equals(btnFlash)) {
            mostrarHeroeController.init(btnFlash.getId(), stage, this);
        }else if (evt.equals(btnAquaman)) {
            mostrarHeroeController.init(btnAquaman.getId(), stage, this);
        }
        
        // Heroes Marvel
        if (evt.equals(btnSpiderman)) {
            mostrarHeroeController.init(btnSpiderman.getId(), stage, this);
        }else if (evt.equals(btnHulk)) {
            mostrarHeroeController.init(btnHulk.getId(), stage, this);
        }else if (evt.equals(btnIroman)) {
            mostrarHeroeController.init(btnIroman.getId(), stage, this);
        }else if (evt.equals(btnHulk)) {
            mostrarHeroeController.init(btnHulk.getId(), stage, this);
        }else if (evt.equals(btnCapitan)) {
            mostrarHeroeController.init(btnCapitan.getId(), stage, this);
        }
            
        stage.show();
        this.heroeController.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}

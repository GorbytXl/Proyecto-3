package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db.mysqlconnect;
import javafx.fxml.Initializable;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Heroes;

public class adminViewController implements Initializable {

    @FXML
    private TableView<Heroes> heroesTable;

    @FXML
    private TableColumn<Heroes, String> idCol;

    @FXML
    private TableColumn<Heroes, String> editorCol;

    @FXML
    private TableColumn<Heroes, String> nombreCol;

    @FXML
    private TableColumn<Heroes, String> alteregoCol;

    @FXML
    private TableColumn<Heroes, String> personajeCol;

    @FXML
    private TableColumn<Heroes, String> fechaCol;

    @FXML
    private TableColumn<?, ?> imagenCol;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnBackAll;

    @FXML
    void getAddView(MouseEvent event) {

    }

    String query = null;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    Heroes heroes = null;

    ObservableList<Heroes> hObservableList = FXCollections.observableArrayList();

    @FXML
    void handleBtnBackAll(ActionEvent event) throws IOException {
        Stage a = new Stage();
        a.initStyle(StageStyle.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("../view/inicioViewFxml.fxml"));
        Stage myStage = (Stage) this.btnBackAll.getScene().getWindow();
        myStage.close();
        Scene scene = new Scene(root);
        a.setScene(scene);
        a.show();

    }

    @FXML
    void handleBtnSalir(ActionEvent event) {
        loginViewController.cerrarVentana(event);

    }

    @FXML
    void refreshTable() throws SQLException {

        try {

            
        hObservableList.clear();
        query = "SELECT * FROM heroes";
        pst = conn.prepareStatement(query);
        rs = pst.executeQuery();
        while (rs.next()) {
            hObservableList.add(new Heroes(
                rs.getInt("id_heroes"),
                rs.getString("editor"), 
                rs.getString("nombre"), 
                rs.getString("alterEgo"), 
                rs.getString("personaje"),
                rs.getDate("fechaAparicion")
                ));
            heroesTable.setItems(hObservableList);
        }

            
        } catch (Exception ex) {
            
        }




    }

    private void loadDate() throws SQLException{
        conn = mysqlconnect.ConnectDB();
        refreshTable();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        editorCol.setCellValueFactory(new PropertyValueFactory<>("editor"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        alteregoCol.setCellValueFactory(new PropertyValueFactory<>("alterEgo"));
        personajeCol.setCellValueFactory(new PropertyValueFactory<>("personaje"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadDate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}

package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.mysql.cj.Query;
import com.mysql.cj.xdevapi.PreparableStatement;

import db.mysqlconnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Heroes;
import Controller.adminViewController;

public class infoAdminViewController implements Initializable {
    @FXML
    private Button btnSalir;

    @FXML
    private Button btnBackAll;

    @FXML
    private TextField editorFld;

    @FXML
    private TextField nombreFld;

    @FXML
    private TextField alteregoFld;

    @FXML
    private TextField personajeFld;

    @FXML
    private DatePicker fechaFld;

    @FXML
    private Button imagenFld;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnLimpiar;

    String query = null;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    Heroes heroes = null;
    private boolean update;
    int heroesId;


    @FXML
    void clean() {
        editorFld.setText(null);
        nombreFld.setText(null);
        alteregoFld.setText(null);
        personajeFld.setText(null);
    

    }

    @FXML
    void handleBtnBackAll(ActionEvent event) throws IOException {
        // Stage a = new Stage();
        // a.initStyle(StageStyle.TRANSPARENT);
        // Parent root = FXMLLoader.load(getClass().getResource("../view/adminViewFxml.fxml"));
        // Stage myStage = (Stage) this.btnBackAll.getScene().getWindow();
        // myStage.close();
        // Scene scene = new Scene(root);
        // a.setScene(scene);
        // a.show();
    }

    @FXML
    void handleBtnSalir(ActionEvent event) {
        loginViewController.cerrarVentana(event);
    }

    @FXML
    void save(MouseEvent event) {
        conn = mysqlconnect.ConnectDB();
        String editor = editorFld.getText();
        String nombre = nombreFld.getText();
        String alterEgo = alteregoFld.getText();
        String personaje = personajeFld.getText();
        String fecha = String.valueOf(fechaFld.getValue());

        if (editor.isEmpty() || nombre.isEmpty() || alterEgo.isEmpty() || personaje.isEmpty() || fecha.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Llenar los datos");
            alert.showAndWait();
        } else {
            getQuery();
            insert();
            clean();
            
            
        }

    }

    private void getQuery() {
        if (update == false) {
            query = "INSERT INTO heroes (editor,nombre,alterEgo,personaje,fechaAparicion) VALUES (?,?,?,?,?)";
        }else{
            query=  "UPDATE heroes SET "
            +"editor=?,"
            +"nombre=?,"
            +"alterEgo=?,"
            +"personaje=?,"
            +"fechaAparicion= ? WHERE id_heroes = "+heroesId+" ";
        }
         
    }

    private void insert() {
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1,editorFld.getText());
            pst.setString(2,nombreFld.getText());
            pst.setString(3,alteregoFld.getText());
            pst.setString(4,personajeFld.getText());
            pst.setString(5,String.valueOf(fechaFld.getValue()));
            pst.execute();

        } catch (SQLException e){
            
        }
            
            
        
    }

    void setTextField(int id, String editor, String nombre, String alterEgo, String personaje, LocalDate tLocalDate){
        heroesId = id;
        editorFld.setText(editor);
        nombreFld.setText(nombre);
        alteregoFld.setText(alterEgo);
        personajeFld.setText(personaje);
        fechaFld.setValue(tLocalDate);
    }

    void setUpdate(boolean b){
        this.update = b;
    }
   
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }
    
}

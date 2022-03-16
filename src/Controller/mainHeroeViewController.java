package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class mainHeroeViewController implements Initializable {
    
    @FXML
    private Button btnBackAll;

    @FXML
    private Button btnSalir;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

}
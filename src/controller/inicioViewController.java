package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class inicioViewController implements Initializable{


    @FXML
    private AnchorPane AnchorPane_Lg;

    @FXML
    private Button btnInicioHeroes;

    @FXML
    private Button btnInicioAdmin;

    @FXML
    private Button btnInicioExit;

    

    @FXML
    void btnHandlerInicio(ActionEvent event) throws IOException {
        Stage a = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/heroeViewFxml.fxml"));
        Stage myStage = (Stage) this.btnInicioAdmin.getScene().getWindow();
        myStage.close();
        Scene scene = new Scene(root);
        a.setScene(scene);
        a.show();

    }

    @FXML
    void handelBtnAccesAdmin(ActionEvent event) throws IOException {
       
        Stage a = new Stage();
        a.initStyle(StageStyle.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("../view/adminViewFxml.fxml"));
        Stage myStage = (Stage) this.btnInicioAdmin.getScene().getWindow();
        myStage.close();
        Scene scene = new Scene(root);
        a.setScene(scene);
        a.show();

    }

    @FXML
    void handleBtnExit(ActionEvent event) {
        loginViewController.cerrarVentana(event);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }


    
}

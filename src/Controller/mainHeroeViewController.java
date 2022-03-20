package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


import db.mysqlconnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class mainHeroeViewController implements Initializable {
    
    @FXML
    private Button btnBackAll;

    @FXML
    private Button btnSalir;

    @FXML
    private ScrollPane idScroll;


    byte [] pimage= null;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String query = null;
    Stage a = new Stage();
    // Nuevas costias
    void mostrarHeroes() throws SQLException{
        // ArrayList<> list = new ArrayList<>();
        conn = mysqlconnect.ConnectDB();
        query = "SELECT id_heroes, imagen FROM heroes";
        pst = conn.prepareStatement(query);
        rs = pst.executeQuery();
        if(rs.next()){
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.NEVER);
            columnConstraints.setPercentWidth(100.00);

            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.NEVER);

            columnConstraints.setPercentWidth(100.0);



            Rectangle rec1 = new Rectangle(40,40, Color.BLUE);
           

            GridPane gridPane = new GridPane();

            // gridPane.setVgap(10);
            // gridPane.setHgap(20);

            gridPane.add(rec1, 0, 0);


            gridPane.getRowConstraints().add(rowConstraints);
            gridPane.getColumnConstraints().add(columnConstraints);

            idScroll.setContent(gridPane);



            

        }
    }


    @FXML
    public void handleBtnBackAll(ActionEvent event) throws IOException {
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
      try {
        mostrarHeroes();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }

}
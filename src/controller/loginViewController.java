package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import db.mysqlconnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class loginViewController implements Initializable {

    @FXML
    private AnchorPane paneLogin;

    @FXML
    private AnchorPane paneSignup;

    @FXML
    private TextField txtUsernameLogin;

    @FXML
    private PasswordField txtPasswordLogin;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignup;

    @FXML
    private TextField txtUsernameSignup;

    @FXML
    private PasswordField txtPasswordSignup;

    @FXML
    private Button btnCreateSignup;

    @FXML
    private Button btnBackLogin;

    @FXML
    private TextField txtEmailSignup;

    @FXML
    private Button btnSalir;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    Stage a = new Stage();

    public static void cerrarVentana(ActionEvent e) {
        Node source = (Node) e.getSource();    
        Stage stage = (Stage) source.getScene().getWindow();    
        stage.close();                          
        }
    
   


    private void limpiarCajas(){
        txtUsernameSignup.setText(null);
        txtEmailSignup.setText(null);
        txtPasswordSignup.setText(null);
                
    }

    @FXML
    void handleBtnBackLogin(ActionEvent event) {
        paneLogin.setVisible(true);
        paneSignup.setVisible(false);
    }

    @FXML
    void handleBtnLogin(ActionEvent event) {
        conn = mysqlconnect.ConnectDB();
        try {
            pst = conn.prepareStatement("SELECT*FROM usuarios WHERE nombreUsuario = ? and password = ?");
            pst.setString(1, txtUsernameLogin.getText());
            pst.setString(2, txtPasswordLogin.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                a.initStyle(StageStyle.TRANSPARENT);
                Parent root = FXMLLoader.load(getClass().getResource("../view/inicioViewFxml.fxml"));
                Stage myStage = (Stage) this.btnLogin.getScene().getWindow();
                myStage.close();
                Scene scene = new Scene(root);
                a.setScene(scene);
                a.show();
            }
        } catch (Exception ex) {

        }

    }

    @FXML
    void handleBtnSignup(ActionEvent event) {
        paneLogin.setVisible(false);
        paneSignup.setVisible(true);

    }

    @FXML
    void handleBtnCreate(ActionEvent event) {
        conn = mysqlconnect.ConnectDB();
        String sql = " INSERT INTO usuarios( nombreUsuario , email , password) VALUES (?,?,?) ";
        try{
            pst= conn.prepareStatement(sql);
            pst.setString(1, txtUsernameSignup.getText());
            pst.setString(2, txtEmailSignup.getText());
            pst.setString(3, txtPasswordSignup.getText());
            int res = pst.executeUpdate();
            if (res >0){
                JOptionPane.showMessageDialog(null,"Datos ingresados");
                limpiarCajas();
            }else{
                JOptionPane.showMessageDialog(null,"Vuelva a Intentarlo");
                limpiarCajas();
            }
            conn.close();
            
        }catch(Exception e){
            System.err.println(e);
        }

    }
    @FXML
    void handleBtnSalir(ActionEvent event) {
        cerrarVentana(event);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

}

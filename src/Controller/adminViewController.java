package Controller;

import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db.mysqlconnect;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
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
    private TableColumn<Heroes, ImageView> imagenCol;

    @FXML
    private TableColumn<Heroes, String> opFld;


    @FXML
    private Button btnSalir;

    @FXML
    private Button btnBackAll;

    @FXML
    private Button btnGetAddView;

    String query = null;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    Heroes heroes = null;

    ObservableList<Heroes> hObservableList = FXCollections.observableArrayList();

    @FXML
    void getAddView(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        // stage.initStyle(StageStyle.UTILITY);
        Parent rootp = FXMLLoader.load(getClass().getResource("../view/infoAdminViewFxml.fxml"));
        Scene scene = new Scene(rootp);
        stage.setScene(scene);
        stage.show();
    }

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
            query = "SELECT * FROM heroes ";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                hObservableList.add(new Heroes(
                        rs.getInt("id_heroes"),
                        rs.getString("editor"),
                        rs.getString("nombre"),
                        rs.getString("alterEgo"),
                        rs.getString("personaje"),
                        rs.getDate("fechaAparicion")));
                        rs.getByte("image");
                heroesTable.setItems(hObservableList);
            }

        } catch (Exception ex) {

        }

    }

    private void loadDate() throws SQLException  {
        conn = mysqlconnect.ConnectDB();
        refreshTable();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        editorCol.setCellValueFactory(new PropertyValueFactory<>("editor"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        alteregoCol.setCellValueFactory(new PropertyValueFactory<>("alterEgo"));
        personajeCol.setCellValueFactory(new PropertyValueFactory<>("personaje"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));

        //add cell of button edit 
        Callback<TableColumn<Heroes, String>, TableCell<Heroes, String>> cellFoctory = (TableColumn<Heroes, String> param) -> {
            // make cell containing buttons
            final TableCell<Heroes, String> cell = new TableCell<Heroes, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                heroes = heroesTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM heroes WHERE id_heroes ="+heroes.getId();
                                conn = mysqlconnect.ConnectDB();
                                pst = conn.prepareStatement(query);
                                pst.execute();
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                // Logger.getLogger(adminViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            heroes = heroesTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../view/infoAdminViewFxml.fxml"));
                            
                            try {
                                loader.load();
                                refreshTable();
                            } catch (IOException | SQLException ex) {
                                // Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            infoAdminViewController addStudentController = loader.getController();
                            addStudentController.setUpdate(true);
                            addStudentController.setTextField(heroes.getId(), heroes.getEditor(), heroes.getNombre(), heroes.getAlterEgo(), heroes.getPersonaje(), heroes.getFechaCreacion().toLocalDate());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.TRANSPARENT);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        opFld.setCellFactory(cellFoctory);
        heroesTable.setItems(hObservableList);
         
         
    }


    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadDate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }}



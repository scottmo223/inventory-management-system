/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Scott
 */
public class MainScreenController implements Initializable {

    @FXML
    private Button partSearch;
    @FXML
    private TextField partSearchInput;
    @FXML
    private TableColumn<?, ?> partPartID;
    @FXML
    private TableColumn<?, ?> partPartName;
    @FXML
    private TableColumn<?, ?> partInventory;
    @FXML
    private TableColumn<?, ?> partPrice;
    @FXML
    private Button partModify;
    @FXML
    private Button partAdd;
    @FXML
    private Button partDelete;
    @FXML
    private Button productSearch;
    @FXML
    private TextField productSearchInput;
    @FXML
    private TableColumn<?, ?> productPartID;
    @FXML
    private TableColumn<?, ?> productPartName;
    @FXML
    private TableColumn<?, ?> productInventory;
    @FXML
    private TableColumn<?, ?> productPrice;
    @FXML
    private Button productModify;
    @FXML
    private Button productAdd;
    @FXML
    private Button productDelete;
    @FXML
    private Button exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchHandler(ActionEvent event) {
    }

    @FXML
    private void modifyHandler(ActionEvent event) throws IOException {
        if(event.getSource() == partModify){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyPart.fxml"));
            Parent mainParent = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modify Part");
            stage.setScene(new Scene(mainParent));  
            stage.show();
        }
        else if(event.getSource() == productModify){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyProduct.fxml"));
            Parent mainParent = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modify Product");
            stage.setScene(new Scene(mainParent));  
            stage.show();
        }        
    }

    @FXML
    private void addHandler(ActionEvent event) throws IOException {
        if(event.getSource() == partAdd){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addPart.fxml"));
            Parent mainParent = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Part");
            stage.setScene(new Scene(mainParent));  
            stage.show();
        }
        else if(event.getSource() == productAdd){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addProduct.fxml"));
            Parent mainParent = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Product");
            stage.setScene(new Scene(mainParent));  
            stage.show();
        }
    }

    @FXML
    private void deleteHandler(ActionEvent event) {
    }

    @FXML
    private void exitHandler(ActionEvent event) {
        Platform.exit();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Scott
 */
public class ModifyProductController implements Initializable {

    @FXML
    private TextField searchInput;
    @FXML
    private TableColumn<?, ?> searchPartID;
    @FXML
    private TableColumn<?, ?> searchPartName;
    @FXML
    private TableColumn<?, ?> searchInventory;
    @FXML
    private TableColumn<?, ?> searchPrice;
    @FXML
    private Button productAdd;
    @FXML
    private Button productDelete;
    @FXML
    private Button exit;
    @FXML
    private Button productSearch;
    @FXML
    private TableColumn<?, ?> productPartID;
    @FXML
    private TableColumn<?, ?> productPartName;
    @FXML
    private TableColumn<?, ?> productInventory;
    @FXML
    private TableColumn<?, ?> productPrice;
    @FXML
    private Button productSave;
    @FXML
    private TextField productID;
    @FXML
    private TextField productName;
    @FXML
    private TextField productInv;
    @FXML
    private TextField productCost;
    @FXML
    private TextField productMax;
    @FXML
    private TextField productMin;

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
    private void saveHandler(ActionEvent event) {
    }
    
    @FXML
    private void deleteHandler(ActionEvent event) {
    }

    @FXML
    private void addHandler(ActionEvent event) {
    }

    @FXML
    private void exitHandler(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
    
}

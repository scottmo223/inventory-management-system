/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemrobertmorales;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author Scott
 */
public class MainScreenController implements Initializable {

    @FXML
    private Button partSearch;
    @FXML
    private TableColumn<?, ?> partPartID;
    @FXML
    private TableColumn<?, ?> partPartName;
    @FXML
    private TableColumn<?, ?> partInventoryLevel;
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
    private TableColumn<?, ?> productPartID;
    @FXML
    private TableColumn<?, ?> productPartName;
    @FXML
    private TableColumn<?, ?> productInventoryLevel;
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
    private void modifyHandler(ActionEvent event) {
    }

    @FXML
    private void addHandler(ActionEvent event) {

    }

    @FXML
    private void deleteHandler(ActionEvent event) {
    }

    @FXML
    private void exitHandler(ActionEvent event) {
    }
    
}

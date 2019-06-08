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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Scott
 */
public class ModifyPartController implements Initializable {

    @FXML
    private Button saveButton;
    @FXML
    private Button exit;
    @FXML
    private RadioButton inhouse;
    @FXML
    private RadioButton outsourced;
    @FXML
    private TextField partID;
    @FXML
    private TextField partName;
    @FXML
    private TextField partInv;
    @FXML
    private TextField partCost;
    @FXML
    private TextField partMax;
    @FXML
    private TextField partMin;
    @FXML
    private TextField partCompanyIdField;
    @FXML
    private TextField partMachineIdField;
    @FXML
    private ToggleGroup partTypeToggleGroup;
    @FXML
    private Label partCompanyIdLabel;
    @FXML
    private Label partMachineIdLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveHandler(ActionEvent event) {
    }

    @FXML
    private void exitAddPart(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void isOutsourced(ActionEvent event) {
        partCompanyIdLabel.setVisible(outsourced.isSelected());
        partCompanyIdField.setVisible(outsourced.isSelected());
        partMachineIdLabel.setVisible(!outsourced.isSelected());
        partMachineIdField.setVisible(!outsourced.isSelected());
    }
   
}

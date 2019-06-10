/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.OutsourcedPart;
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
import Model.Part;

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
    Part part;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveHandler(ActionEvent event) {
        int id = Integer.parseInt(partID.getText());
        String name = partName.getText();
        int stock = Integer.parseInt(partInv.getText());
        double price = Double.parseDouble(partCost.getText());
        int max = Integer.parseInt(partMax.getText());
        int min = Integer.parseInt(partMin.getText());
        String company = partCompanyIdField.getText();
        int machineId = inhouse.isSelected() ? Integer.parseInt(partMachineIdField.getText()) : 0;
        
        Part updatedPart = inhouse.isSelected() ? new InhousePart(id, name, price, stock, min, max, machineId) : new OutsourcedPart(id, name, price, stock, min, max, company);
        int partIndex = Model.Inventory.getAllParts().indexOf(Model.Inventory.lookupPart(id));
        
        Model.Inventory.updatePart(partIndex, updatedPart);
        
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
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
    
    public void setPart(Part part) {
        this.part = part;
        
        partID.setText(Integer.toString(part.getId()));
        partName.setText(part.getName());
        partInv.setText(Integer.toString(part.getStock()));
        partCost.setText(Double.toString(part.getPrice()));
        partMin.setText(Integer.toString(part.getMin()));
        partMax.setText(Integer.toString(part.getMax()));
        
        if(part instanceof OutsourcedPart) {
            partCompanyIdField.setText(((OutsourcedPart) part).getCompanyName());
            outsourced.setSelected(true);
            partCompanyIdLabel.setVisible(outsourced.isSelected());
            partCompanyIdField.setVisible(outsourced.isSelected());
            partMachineIdLabel.setVisible(!outsourced.isSelected());
            partMachineIdField.setVisible(!outsourced.isSelected());
        }
        else if (part instanceof InhousePart) {
            partMachineIdField.setText(Integer.toString(((InhousePart) part).getMachineId()));
        }
    }
}
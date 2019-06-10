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

/**
 * FXML Controller class
 *
 * @author Scott.Morales
 */
public class AddPartController implements Initializable {

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
        int id;
        try {        //no duplicate part #'s and start at 1 if no parts
            id = Model.Inventory.getAllParts().get(Model.Inventory.getAllParts().size()-1).getId() + 1;
        } catch (IndexOutOfBoundsException e) {
            id = 1;
        }
        String name = partName.getText();
        int stock = Integer.parseInt(partInv.getText());
        double price = Double.parseDouble(partCost.getText());
        int max = Integer.parseInt(partMax.getText());
        int min = Integer.parseInt(partMin.getText());
        String company = partCompanyIdField.getText();
        int machineId = inhouse.isSelected() ? Integer.parseInt(partMachineIdField.getText()) : 0;
        
        Model.Inventory.addPart(inhouse.isSelected() ? new InhousePart(id, name, price, stock, min, max, machineId) : new OutsourcedPart(id, name, price, stock, min, max, company));
        
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
}
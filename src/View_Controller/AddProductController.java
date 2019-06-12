package View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Part;
import Model.Inventory;
import Model.Product;
import java.text.NumberFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Scott.Morales
 */
public class AddProductController implements Initializable {

    @FXML
    private Button partSearch;
    @FXML
    private TextField searchInput;
    @FXML
    private TableView<Part> partTableViewAll;
    @FXML
    private TableColumn<Part, Integer> partPartIDAll;
    @FXML
    private TableColumn<Part, String> partPartNameAll;
    @FXML
    private TableColumn<Part, Integer> partInventoryAll;
    @FXML
    private TableColumn<Part, Double> partPriceAll;
    @FXML
    private TableView<Part> partTableViewProduct;
    @FXML
    private TableColumn<Part, Integer> partPartIDProduct;
    @FXML
    private TableColumn<Part, String> partPartNameProduct;
    @FXML
    private TableColumn<Part, Integer> partInventoryProduct;
    @FXML
    private TableColumn<Part, Double> partPriceProduct;
    @FXML
    private Button productAdd;
    @FXML
    private Button productDelete;
    @FXML
    private Button exit;
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
    private Button productSave;
    private ObservableList<Part> addedParts = FXCollections.observableArrayList();
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        partTableViewAll.setItems(Inventory.getAllParts());
        partPartIDAll.setCellValueFactory(new PropertyValueFactory<>("id"));
        partInventoryAll.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPartNameAll.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceAll.setCellValueFactory(new PropertyValueFactory<>("price"));
        partPriceAll.setCellFactory(tc -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                setText(empty ? null : currencyFormat.format(price));
                setAlignment(Pos.CENTER_RIGHT);
            }
        });
        
        partTableViewProduct.setItems(addedParts);
        partPartIDProduct.setCellValueFactory(new PropertyValueFactory<>("id"));
        partInventoryProduct.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPartNameProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceProduct.setCellValueFactory(new PropertyValueFactory<>("price"));
        partPriceProduct.setCellFactory(tc -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                setText(empty ? null : currencyFormat.format(price));
                setAlignment(Pos.CENTER_RIGHT);
            }
        });
    }    

    @FXML
    private void searchHandler(ActionEvent event) {
        try {
                int searchPartId = Integer.parseInt(searchInput.getText());
                Part searchedPart = Model.Inventory.lookupPart(searchPartId);
                partTableViewAll.getSelectionModel().select(searchedPart);
            } catch (NumberFormatException e) {
                String searchPartName = searchInput.getText();
                partTableViewAll.setItems(Model.Inventory.lookupPart(searchPartName));
            }
    }

    @FXML
    private void deleteHandler(ActionEvent event) {
        Part part = partTableViewProduct.getSelectionModel().getSelectedItem();
        addedParts.remove(part);
    }

    @FXML
    private void addHandler(ActionEvent event) {
        Part part = partTableViewAll.getSelectionModel().getSelectedItem();
        addedParts.add(part);
    }

    @FXML
    private void exitHandler(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveHandler(ActionEvent event) {
        int id;
        try {        //no duplicate part #'s and start at 1 if no parts
            id = Model.Inventory.getAllProducts().get(Model.Inventory.getAllProducts().size()-1).getId() + 1;
        } catch (IndexOutOfBoundsException e) {
            id = 1;
        }
        String name = partName.getText();
        int stock = Integer.parseInt(partInv.getText());
        double price = Double.parseDouble(partCost.getText());
        int max = Integer.parseInt(partMax.getText());
        int min = Integer.parseInt(partMin.getText());
        
        if (max <= min) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Check Inventory Input");
            alert.setContentText("Max value must be greater than Min");
            alert.showAndWait();
        } else {
            Product newProduct = new Product(id, name, price, stock, min, max);
            for (Part addedPart : addedParts) {
                newProduct.addAssociatedPart(addedPart);
            }

            Model.Inventory.addProduct(newProduct);

            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        }
    }
    
    @FXML
    private void resetTable(KeyEvent event) {
        partTableViewAll.setItems(Inventory.getAllParts());
    }
}
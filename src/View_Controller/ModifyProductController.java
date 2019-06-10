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
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Scott.Morales
 */
public class ModifyProductController implements Initializable {

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
    private TextField searchInput;
    @FXML
    private Button productAdd;
    @FXML
    private Button productDelete;
    @FXML
    private Button exit;
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
    @FXML
    private Button partSearch;
    private Product product;
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
    private void saveHandler(ActionEvent event) {
        int id = Integer.parseInt(productID.getText());
        String name = productName.getText();
        int stock = Integer.parseInt(productInv.getText());
        double price = Double.parseDouble(productCost.getText());
        int max = Integer.parseInt(productMax.getText());
        int min = Integer.parseInt(productMin.getText());
                
        Product updatedProduct = new Product(id, name, price, stock, min, max);
        for (Part addedPart : addedParts) {
            updatedProduct.addAssociatedPart(addedPart);
        }
        int productIndex = Model.Inventory.getAllProducts().indexOf(Model.Inventory.lookupProduct(id));
        
        Model.Inventory.updateProduct(productIndex, updatedProduct);
        
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
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
    private void resetTable(KeyEvent event) {
        partTableViewAll.setItems(Inventory.getAllParts());
    }
    
    public void setProduct(Product product) {
        this.product = product;
        
        productID.setText(Integer.toString(product.getId()));
        productName.setText(product.getName());
        productInv.setText(Integer.toString(product.getStock()));
        productCost.setText(Double.toString(product.getPrice()));
        productMin.setText(Integer.toString(product.getMin()));
        productMax.setText(Integer.toString(product.getMax()));
        
        for (Part loadedPart : product.getAllAssociatedParts()) {
            addedParts.add(loadedPart);
        }
    }
}
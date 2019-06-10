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
import javafx.stage.Modality;
import javafx.stage.Stage;
import Model.Part;
import Model.Inventory;
import Model.Product;
import java.text.NumberFormat;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Cell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Scott.Morales
 */
public class MainScreenController implements Initializable {

    @FXML
    private Button partSearch;
    @FXML
    private TextField partSearchInput;
    @FXML
    private TableColumn<Part, Integer> partPartID;
    @FXML
    private TableColumn<Part, String> partPartName;
    @FXML
    private TableColumn<Part, Integer> partInventory;
    @FXML
    private TableColumn<Part, Double> partPrice;
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
    private TableColumn<Product, Integer> productPartID;
    @FXML
    private TableColumn<Product, String> productPartName;
    @FXML
    private TableColumn<Product, Integer> productInventory;
    @FXML
    private TableColumn<Product, Double> productPrice;
    @FXML
    private Button productModify;
    @FXML
    private Button productAdd;
    @FXML
    private Button productDelete;
    @FXML
    private Button exit;
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableView<Product> productTableView;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO (executed as soon as screen loads up)
        partTableView.setItems(Inventory.getAllParts());
        partPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partPrice.setCellFactory(tc -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                setText(empty ? null : currencyFormat.format(price));
                setAlignment(Pos.CENTER_RIGHT);
            }
        });
        
        productTableView.setItems(Inventory.getAllProducts());
        productPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        productPrice.setCellFactory(tc -> new TableCell<Product, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                setText(empty ? null : currencyFormat.format(price));
                setAlignment(Pos.CENTER_RIGHT);
            }
        });
    }
        
    @FXML
    private void searchHandler(ActionEvent event) {
        if(event.getSource() == partSearch){
            try {
                int searchPartId = Integer.parseInt(partSearchInput.getText());
                Part searchedPart = Model.Inventory.lookupPart(searchPartId);
                partTableView.getSelectionModel().select(searchedPart);
            } catch (NumberFormatException e) {
                String searchPartName = partSearchInput.getText();
                partTableView.setItems(Model.Inventory.lookupPart(searchPartName));
            }
            
        }
        else if(event.getSource() == productSearch){
            try {    
                int searchProductId = Integer.parseInt(productSearchInput.getText());
                Product searchedProduct = Model.Inventory.lookupProduct(searchProductId);
                productTableView.getSelectionModel().select(searchedProduct);
            } catch (NumberFormatException e) {
                String searchProductName = productSearchInput.getText();
                productTableView.setItems(Model.Inventory.lookupProduct(searchProductName));
            }
        }
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
            ModifyPartController controller = fxmlLoader.getController();
            Part part = partTableView.getSelectionModel().getSelectedItem();
            controller.setPart(part);
        }
        else if(event.getSource() == productModify){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyProduct.fxml"));
            Parent mainParent = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modify Product");
            stage.setScene(new Scene(mainParent));  
            stage.show();
            ModifyProductController controller = fxmlLoader.getController();
            Product product = productTableView.getSelectionModel().getSelectedItem();
            controller.setProduct(product);
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
        if(event.getSource() == partDelete) {
            Part part = partTableView.getSelectionModel().getSelectedItem();
            Model.Inventory.deletePart(part);
        }
        else if(event.getSource() == productDelete){
            Product product = productTableView.getSelectionModel().getSelectedItem();
            Model.Inventory.deleteProduct(product);
        }
    }

    @FXML
    private void exitHandler(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void resetTable(KeyEvent event) {
        if(event.getSource() == partSearchInput) partTableView.setItems(Inventory.getAllParts());
        else if(event.getSource() == productSearchInput) productTableView.setItems(Inventory.getAllProducts());
    }
}
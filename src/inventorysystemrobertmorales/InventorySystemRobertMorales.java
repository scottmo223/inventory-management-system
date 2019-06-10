package inventorysystemrobertmorales;

import Model.InhousePart;
import Model.Inventory;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Scott.Morales
 */
public class InventorySystemRobertMorales extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/mainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("SMorales Inventory");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         InhousePart part1 = new InhousePart(1, "Part 1", 12.95, 19, 10, 20, 452);
         InhousePart part2 = new InhousePart(2, "Part 2", 14.95, 6, 3, 15, 419);
         InhousePart part3 = new InhousePart(3, "Part 3", 200.01, 100, 50, 1000, 3);
         
         Product prod1 = new Product(1, "Product 1", 1002.30, 50, 40, 100);
         Product prod2 = new Product(2, "Product 2", 1232.51, 40, 20, 600);
         Product prod3 = new Product(3, "Product 3", 10.05, 10, 400, 1000);
         
         prod1.addAssociatedPart(part1);
         prod2.addAssociatedPart(part2);
         prod2.addAssociatedPart(part3);
         
         Inventory.addProduct(prod1);
         Inventory.addProduct(prod2);
         Inventory.addProduct(prod3);
         
         Inventory.addPart(part1);
         Inventory.addPart(part2);
         Inventory.addPart(part3);
        
        launch(args);
    }
    
}
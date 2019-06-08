/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Scott
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    public static void addPart(Part part){
        allParts.add(part);
    }
    public static void addProduct(Product product){
        allProducts.add(product);
    }
    public static Part lookupPart(int partId){
        for(Part searchedPart : Model.Inventory.getAllParts()){
            if (searchedPart.getId() == partId) {
                return searchedPart;
            }            
        }
        System.out.println("Not found");
        return null;
    }
    public static Product lookupProduct(int productId){
        for(Product searchedProduct : Model.Inventory.getAllProducts()){
            if (searchedProduct.getId() == productId) {
                return searchedProduct;
            }            
        }
        System.out.println("Not found");
        return null;
    }
    public ObservableList<Part> lookupPart(String partName){
        return null;
    }
    public ObservableList<Product> lookupProduct(String productName){
        return null;
    }
    public static void updatePart(int index, Part part){
        getAllParts().set(index, part);
    }
    public void updateProduct(int index, Product product){
        getAllProducts().set(index, product);
    }
    public static void deletePart(Part part){
        allParts.remove(part);
    }
    public static void deleteProduct(Product product){
        allProducts.remove(product);
    }
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
//the bottom two are members of getSelectionModel()
//use getSelectedItem() to return object from TableView.
//select() will automatically select a row or object in a table.
//ex: partTableView.getSelectionModel().select(part);
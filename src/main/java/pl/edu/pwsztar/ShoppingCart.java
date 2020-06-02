package pl.edu.pwsztar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart implements ShoppingCartOperation {

    private final List<Product> productList;

    public ShoppingCart() {
        this.productList = new ArrayList<>();
    }

    public boolean addProducts(String productName, int price, int quantity) {
        if(getAllProductsQuantity() + quantity > ShoppingCart.PRODUCTS_LIMIT || price <= 0 || quantity <= 0){
            return false;
        }
        productName = productName.toLowerCase();

        if(isProductAlreadyAdded(productName)){
            for(Product product: productList){
                if(product.getProductName().equals(productName) && product.getPrice() != price){
                    product.setAmount(product.getAmount() + quantity);
                    return true;
                }
            }
        }else{
            productList.add(new Product(productName, price, quantity));
            return true;
        }
        return false;
    }

    public boolean deleteProducts(String productName, int quantity) {
        if(quantity <=0 ){
            return false;
        }
        productName = productName.toLowerCase();
        for(Product product: productList){
            if(product.getProductName().equals(productName)){
                if(product.getAmount() > quantity){
                    product.setAmount(product.getAmount() - quantity);
                    return true;
                }else if(product.getAmount() == quantity){
                    productList.remove(product);
                    return true;
                }

            }
        }
        return false;
    }

    public int getQuantityOfProduct(String productName) {
        return productList.stream()
                .filter(product -> product.getProductName().equals(productName.toLowerCase()))
                .mapToInt(Product::getAmount).sum();
    }

    public int getSumProductsPrices() {
       return productList.stream().mapToInt(product -> product.getPrice() * product.getAmount()).sum();
    }

    public int getProductPrice(String productName) {
        productName = productName.toLowerCase();

        for(Product product: productList){

            if(product.getProductName().equals(productName)){
                return product.getPrice();
            }
        }
        return 0;
    }

    public List<String> getProductsNames() {
        return productList.stream().map(product ->
                product.getProductName().substring(0,1).toUpperCase() + product.getProductName().substring(1))
                .collect(Collectors.toList());

    }

    private boolean isProductAlreadyAdded(String name){
        return productList.stream()
                .anyMatch(product -> product.getProductName()
                .equals(name));
    }

    private  int getAllProductsQuantity(){
        return productList.stream()
                .mapToInt(product -> product.getPrice() * product.getAmount())
                .sum();
    }
}

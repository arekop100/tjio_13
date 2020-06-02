package pl.edu.pwsztar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    @DisplayName("Should add product to Shopping Cart")
    @ParameterizedTest
    @CsvSource({
            "Banana, 30, 2",
            "Apple, 10, 1",
            "orange, 2, 5"
    })
    void shouldAddProductToShoppingCart(String productName, int price, int amount) {
        //given
            final ShoppingCart shoppingCart = new ShoppingCart();
        //when
            final boolean result = shoppingCart.addProducts(productName, price, amount);
        //then
        assertTrue(result);
    }

    @DisplayName("shouldn't add product to Shopping Cart")
    @ParameterizedTest
    @CsvSource({
            "Banana, -30, 2",
            "Apple, 10, 0",
            "orange, 2, -5"
    })
    void shouldNotAddProductToShoppingCart(String productName, int price, int amount) {
        //given
        final ShoppingCart shoppingCart = new ShoppingCart();
        //when
        final boolean result = shoppingCart.addProducts(productName, price, amount);
        //then
        assertFalse(result);
    }

    @DisplayName("Should delete product from Shopping Cart")
    @ParameterizedTest
    @CsvSource({
            "Banana, 2",
            "Apple,  1",
            "orange, 5"
    })
    void shouldDeleteProductFromShoppingCart(String productName, int amount) {
        //given
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProducts("Banana", 20, 4);
        shoppingCart.addProducts("Apple", 20, 4);
        shoppingCart.addProducts("Orange", 20, 5);
        //when
        final boolean result = shoppingCart.deleteProducts(productName, amount);
        //then
        assertTrue(result);
    }

    @DisplayName("Shouldn't delete product from Shopping Cart")
    @ParameterizedTest
    @CsvSource({
            "Banana, 2",
            "Apple,  1",
            "orange, 5"
    })
    void shouldNotDeleteProductFromShoppingCart(String productName, int amount) {
        //given
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProducts("Banana", 20, 1);
        shoppingCart.addProducts("Apple", 20, 0);
        shoppingCart.addProducts("Orange", 20, 3);
        //when
        final boolean result = shoppingCart.deleteProducts(productName, amount);
        //then
        assertFalse(result);
    }

}

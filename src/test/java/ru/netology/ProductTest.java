package ru.netology;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    public void productWithNegativeCost(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            new Product("Product", -1L);
        });
        assertEquals("\"cost\" mast be not negative", exception.getMessage());
    }

    @Test
    public void productWithZeroCost(){
        Product product = new Product("Product", 0L);
        String expectedName = "Product";
        String actualName = product.getName();

        assertEquals(expectedName, actualName);

        long expectedCost = 0L;
        long actualCost = product.getCost();

        assertEquals(expectedCost, actualCost);
    }

    @Test
    public void productHashCodeEquals(){
        Product product1 = new Product("Product", 100L);
        Product product2 = new Product("Product", 100L);

        boolean expected = product1.hashCode() == product2.hashCode();
        boolean actual = true;
        assertEquals(expected, actual);
    }

    @Test
    public void productHashCodeNotEqualsName(){
        Product product1 = new Product("Product", 100L);
        Product product2 = new Product("Product1", 100L);

        boolean expected = product1.hashCode() == product2.hashCode();
        boolean actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void productHashCodeNotEqualsCost(){
        Product product1 = new Product("Product", 100L);
        Product product2 = new Product("Product", 101L);

        boolean expected = product1.hashCode() == product2.hashCode();
        boolean actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void productHashCodeNotEquals(){
        Product product1 = new Product("Product", 100L);
        Product product2 = new Product("Product1", 101L);

        boolean expected = product1.hashCode() == product2.hashCode();
        boolean actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void productNotEqualType(){
        Product product1 = new Product("Product", 100L);
        String product2 = "Product";

        boolean expected = product1.equals(product2);
        boolean actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void productNotEqualName(){
        Product product1 = new Product("Product", 100L);
        Product product2 = new Product("Product1", 100L);

        boolean expected = product1.equals(product2);
        boolean actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void productNotEqualCost(){
        Product product1 = new Product("Product", 100L);
        Product product2 = new Product("Product", 101L);

        boolean expected = product1.equals(product2);
        boolean actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void productNotEqualCostAndName(){
        Product product1 = new Product("Product", 100L);
        Product product2 = new Product("Product1", 101L);

        boolean expected = product1.equals(product2);
        boolean actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void productEqualHimself(){
        Product product1 = new Product("Product", 100L);
        Product product2 = product1;

        boolean expected = product1.equals(product2);
        boolean actual = true;
        assertEquals(expected, actual);
    }

    @Test
    public void productEqual(){
        Product product1 = new Product("Product", 100L);
        Product product2 = new Product("Product", 100L);

        boolean expected = product1.equals(product2);
        boolean actual = true;
        assertEquals(expected, actual);
    }
}
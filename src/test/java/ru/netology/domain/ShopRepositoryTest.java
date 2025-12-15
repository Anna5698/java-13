package ru.netology.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    @Test
    void shouldRemoveExistingProduct() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(2, "Product 2", 200);
        Product product3 = new Product(3, "Product 3", 300);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        // Act
        repository.removeById(2);

        // Assert
        Product[] products = repository.findAll();
        assertEquals(2, products.length);
        assertEquals(1, products[0].getId());
        assertEquals(3, products[1].getId());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenRemovingNonExistingProduct() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(2, "Product 2", 200);

        repository.add(product1);
        repository.add(product2);

        // Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> repository.removeById(3)
        );

        assertEquals("Element with id: 3 not found", exception.getMessage());
    }

    @Test
    void shouldFindProductById() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product expectedProduct = new Product(1, "Test Product", 100);
        repository.add(expectedProduct);

        // Act
        Product foundProduct = repository.findById(1);

        // Assert
        assertNotNull(foundProduct);
        assertEquals(expectedProduct, foundProduct);
        assertEquals(1, foundProduct.getId());
        assertEquals("Test Product", foundProduct.getTitle());
        assertEquals(100, foundProduct.getPrice());
    }

    @Test
    void shouldReturnNullWhenProductNotFound() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        repository.add(new Product(1, "Product 1", 100));

        // Act
        Product foundProduct = repository.findById(2);

        // Assert
        assertNull(foundProduct);
    }
}

package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class ProductRepositoryTest {

    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ProductRepository();
    }

    @Test
    void saveOneNewProduct() {
        repository.save(new Product("one", 10L));
        Product[] expected = new Product[]{new Product("one", 10L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void saveOneNewBook() {
        repository.save(new Book("one", "Author", 10L));
        Product[] expected = new Product[]{new Book("one", "Author", 10L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void saveOneNewSmartphone() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        Product[] expected = new Product[]{new Smartphone("one", "Manufacturer", 10L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void saveSmartphoneBookAndProduct() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));
        Product[] expected = new Product[]{
                new Smartphone("one", "Manufacturer", 10L),
                new Book("two", "Author", 11L),
                new Product("one",  112L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdMiddle() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));

        repository.removeById(repository.findAll()[1].getId());

        Product[] expected = new Product[]{
                new Smartphone("one", "Manufacturer", 10L),
                new Product("one",  112L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdBegin() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));

        repository.removeById(repository.findAll()[0].getId());

        Product[] expected = new Product[]{
                new Book("two", "Author", 11L),
                new Product("one",  112L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdEnd() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));

        repository.removeById(repository.findAll()[2].getId());

        Product[] expected = new Product[]{
                new Smartphone("one", "Manufacturer", 10L),
                new Book("two", "Author", 11L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByUnknownId() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));

        Exception exception = assertThrows(NotFoundException.class, () -> {
            repository.removeById(100);
        });

        assertEquals("There is no product with id 100", exception.getMessage());
    }

    @Test
    void createByOtherRepo() {
        Product[] repository = new Product[]{
                new Smartphone("one", "Manufacturer", 10L),
                new Book("two", "Author", 11L),
                new Product("one",  112L)};

        this.repository = new ProductRepository(repository);

        Product[] expected = new Product[]{
                new Smartphone("one", "Manufacturer", 10L),
                new Book("two", "Author", 11L),
                new Product("one",  112L)};
        Product[] actual = this.repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void findByIdMiddle() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));

        Product expected = new Book("two", "Author", 11L);
        Product actual = repository.findById(repository.findAll()[1].getId());

        assertEquals(expected, actual);
    }

    @Test
    void findByIdBegin() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));

        Product expected = new Smartphone("one", "Manufacturer", 10L);
        Product actual = repository.findById(repository.findAll()[0].getId());

        assertEquals(expected, actual);
    }

    @Test
    void findByIdEnd() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));

        Product expected = new Product("one",  112L);
        Product actual = repository.findById(repository.findAll()[2].getId());

        assertEquals(expected, actual);
    }

    @Test
    void findByUnknownId() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));

        Product expected = null;
        Product actual = repository.findById(100);

        assertEquals(expected, actual);
    }

    @Test
    void doubleAddProduct() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        Book book = new Book("two", "Author", 11L);
        repository.save(book);
        repository.save(new Product("one",  112L));

        Exception exception = assertThrows(AlreadyExistsException.class, ()->{
            repository.save(book);
        });

        assertEquals("There is already a product with id " + book.getId(), exception.getMessage());
    }

    @Test
    void addSameId() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        Book book = new Book("two", "Author", 11L);
        repository.save(book);
        int id = book.getId();
        repository.save(new Product("one",  112L));

        Product product = Mockito.mock(Product.class);
        doReturn(id).when(product).getId();

        Exception exception = assertThrows(AlreadyExistsException.class, ()->{
            repository.save(product);
        });

        assertEquals("There is already a product with id " + id, exception.getMessage());
    }
}
package ru.netology;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ProductRepository {
    private Product[] repo;

    public ProductRepository(@NotNull Product[] repo) {
        this.repo = repo;
    }

    public ProductRepository() {
        repo = new Product[0];
    }

    public void save(@NotNull Product product) {
        int id = product.getId();
        if (findById(id) != null) {
            throw new AlreadyExistsException("There is already a product with id " + id);
        }
        Product[] tmp = Arrays.copyOf(repo, repo.length + 1);
        tmp[repo.length] = product;
        repo = tmp;
    }

    public Product[] findAll() {
        return repo.clone();
    }

    public Product findById(int itemId) {
        for (int i = 0; i < repo.length; i++) {
            if (repo[i].getId() == itemId) {
                return repo[i];
            }
        }
        return null;
    }

    public void removeById(int itemId) {
        // Здесь 4 строки кода которые в задании прописаны, но они не эффективны,
        // потому что делают лишний пробег по элементам массива,
        // поэтому они закомментированы. Нужное исключение бросается в конце кода текущего метода.
        // Product product = findById(itemId);
        // if (product == null) {
        //     throw new NotFoundException("There is no product with id " + itemId);
        // }
        for (int i = 0; i < repo.length; i++) {
            if (repo[i].getId() == itemId) {
                remove(i);
                return;
            }
        }
        throw new NotFoundException("There is no product with id " + itemId);
    }

    private void remove(int index) throws NullPointerException {
        Product[] tmp = new Product[repo.length - 1];
        int i = 0;
        for (; i < index; i++) {
            tmp[i] = repo[i];
        }
        i++;
        for (; i < repo.length; i++) {
            tmp[i - 1] = repo[i];
        }
        repo = tmp;
    }
}

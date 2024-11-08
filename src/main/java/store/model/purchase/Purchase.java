package store.model.purchase;

import store.model.product.Product;
import store.model.product.Products;

public class Purchase {
    private final Product product;
    private final int count;

    public Purchase(final String productName, final int count) {
        this.product = Products.getProductByName(productName);
        this.count = count;
    }
}

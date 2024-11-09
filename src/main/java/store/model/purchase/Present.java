package store.model.purchase;

import store.model.product.Product;

public class Present {
    private final Product product;
    private final int amount;

    public Present(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }
}

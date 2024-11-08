package store.controller;

import store.model.product.Products;
import store.view.ProductView;

public class StoreController {
    private final ProductView productView = new ProductView();
    private final Products products = Products.getInstance();

    public void sale() {
        productView.displayProducts(products);
    }
}

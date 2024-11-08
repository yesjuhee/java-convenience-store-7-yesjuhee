package store.controller;

import store.constants.ProductsFile;
import store.constants.PromotionsFile;
import store.model.product.Products;
import store.model.promotion.Promotions;
import store.view.ProductView;

public class StoreController {
    private final ProductView productView = new ProductView();
    private final Promotions promotions = Promotions.getInstance();
    private final Products products = Products.getInstance();

    public void sale() {
        promotions.fetchPromotionsData(PromotionsFile.FILE_PATH);
        products.fetchProductsData(ProductsFile.FILE_PATH);
        productView.displayProducts(products);
    }
}

package store.controller;

import store.constants.ProductsFile;
import store.constants.PromotionsFile;
import store.model.product.ProductDatabase;
import store.model.promotion.Promotions;

public class ProductController extends StoreController {
    public void displayProducts() {
        fetchFileData();
        productView.displayProducts(ProductDatabase.getProducts());
    }

    private void fetchFileData() {
        Promotions.fetchPromotionsData(PromotionsFile.FILE_PATH);
        ProductDatabase.fetchProductsFile(ProductsFile.FILE_PATH);
    }
}

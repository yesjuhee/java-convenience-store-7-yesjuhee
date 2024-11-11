package store.controller;

import store.constants.ProductsFile;
import store.constants.PromotionsFile;
import store.model.product.Products;
import store.model.promotion.Promotions;

public class ProductController extends StoreController {
    public void displayProducts() {
        fetchFileData();
        productView.displayProducts(Products.getProducts());
    }

    private void fetchFileData() {
        Promotions.fetchPromotionsData(PromotionsFile.FILE_PATH);
        Products.fetchProductsData(ProductsFile.FILE_PATH);
    }
}

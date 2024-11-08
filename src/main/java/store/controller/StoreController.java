package store.controller;

import store.constants.ProductsFile;
import store.constants.PromotionsFile;
import store.model.product.Products;
import store.model.promotion.Promotions;
import store.model.purchase.Purchases;
import store.view.ProductView;
import store.view.PurchaseView;

public class StoreController {
    private Purchases purchases;

    public void run() {
        fetchFileData();
        displayProducts();
        purchaseProducts();
    }

    private void fetchFileData() {
        Promotions.fetchPromotionsData(PromotionsFile.FILE_PATH);
        Products.fetchProductsData(ProductsFile.FILE_PATH);
    }

    private void displayProducts() {
        ProductView productView = new ProductView();
        productView.displayProducts(Products.getProducts());
    }

    private void purchaseProducts() {
        PurchaseView purchaseView = new PurchaseView();
        String purchaseInput = purchaseView.readPurchaseInfo();
        this.purchases = new Purchases(purchaseInput);
    }
}

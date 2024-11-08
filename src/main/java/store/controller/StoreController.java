package store.controller;

import java.util.function.Supplier;
import store.constants.ProductsFile;
import store.constants.PromotionsFile;
import store.model.product.Products;
import store.model.promotion.Promotions;
import store.model.purchase.Purchases;
import store.view.ErrorView;
import store.view.ProductView;
import store.view.PurchaseView;

public class StoreController {
    public void run() {
        fetchFileData();
        displayProducts();
        Purchases purchases = retryUntilSuccess(() -> purchaseProducts());
    }

    private void fetchFileData() {
        Promotions.fetchPromotionsData(PromotionsFile.FILE_PATH);
        Products.fetchProductsData(ProductsFile.FILE_PATH);
    }

    private void displayProducts() {
        ProductView productView = new ProductView();
        productView.displayProducts(Products.getProducts());
    }

    private Purchases purchaseProducts() {
        PurchaseView purchaseView = new PurchaseView();
        String purchaseInput = purchaseView.readPurchaseInfo();
        return new Purchases(purchaseInput);
    }

    private <T> T retryUntilSuccess(Supplier<T> supplier) {
        ErrorView errorView = new ErrorView();

        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                errorView.displayError(exception.getMessage());
            }
        }
    }
}

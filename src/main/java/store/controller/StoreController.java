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
        displayProducts();
        purchaseProducts();
    }

    private void displayProducts() {
        Promotions promotions = Promotions.getInstance();
        Products products = Products.getInstance();
        ProductView productView = new ProductView();
        promotions.fetchPromotionsData(PromotionsFile.FILE_PATH);
        products.fetchProductsData(ProductsFile.FILE_PATH);
        productView.displayProducts(products);
    }

    private void purchaseProducts() {
        PurchaseView purchaseView = new PurchaseView();
        String purchaseInput = purchaseView.readPurchaseInfo();
        this.purchases = new Purchases(purchaseInput);
    }
}

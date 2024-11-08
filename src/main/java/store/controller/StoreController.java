package store.controller;

import store.constants.ProductsFile;
import store.constants.PromotionsFile;
import store.model.product.Products;
import store.model.promotion.Promotions;
import store.model.purchase.Purchases;
import store.view.ProductView;
import store.view.PurchaseView;

public class StoreController {
    private final ProductView productView = new ProductView();
    private final PurchaseView purchaseView = new PurchaseView();

    private final Promotions promotions = Promotions.getInstance();
    private final Products products = Products.getInstance();
    private Purchases purchases;

    public void sale() {
        promotions.fetchPromotionsData(PromotionsFile.FILE_PATH);
        products.fetchProductsData(ProductsFile.FILE_PATH);
        productView.displayProducts(products);
    }

    public void purchase() {
        String purchaseInput = purchaseView.readPurchaseInfo();
        this.purchases = new Purchases(purchaseInput);
    }
}

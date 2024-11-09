package store.controller;

import java.util.function.Supplier;
import store.constants.ProductsFile;
import store.constants.PromotionsFile;
import store.model.product.Products;
import store.model.promotion.Promotions;
import store.model.purchase.Presents;
import store.model.purchase.Purchase;
import store.model.purchase.Purchases;
import store.view.ErrorView;
import store.view.ProductView;
import store.view.PurchaseView;

public class StoreController {
    private final PurchaseView purchaseView = new PurchaseView();
    private Purchases purchases;
    private Presents presents;

    public void run() {
        fetchFileData();
        displayProducts();
        purchases = retryUntilSuccess(() -> readPurchaseInfo());
        presents = new Presents();
        purchases.getPurchases().forEach(this::confirmPurchase);
//        Presents presents = retryUntilSuccess(() -> applyPromotion(purchases)); // 프로모션 적용 상품 개수 확정
//        Purchases finalPurchases = retryUntilSuccess(() -> applyNoPromotion(purchases));

    }

    private void fetchFileData() {
        Promotions.fetchPromotionsData(PromotionsFile.FILE_PATH);
        Products.fetchProductsData(ProductsFile.FILE_PATH);
    }

    private void displayProducts() {
        ProductView productView = new ProductView();
        productView.displayProducts(Products.getProducts());
    }

    private Purchases readPurchaseInfo() {
        String purchaseInput = purchaseView.readPurchaseInfo();
        return new Purchases(purchaseInput);
    }

    private void confirmPurchase(Purchase purchase) {
        if (!purchase.canApplyPromotion()) {
            purchase.purchaseWithoutPromotion();
            return; // 변동 없음
        }
        System.out.println("프로모션 적용");
    }

//    private Presents applyPromotion(final Purchases purchases) {
////        boolean reply = purchaseView.confirmToAddPromotionProduct();
////        System.out.println(reply + "\n");
//        return null;
//    }

//    private Purchases applyNoPromotion(Purchases purchases) {
//    }

    // 멤버십

    // 영수증

    private <T> T retryUntilSuccess(final Supplier<T> supplier) {
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

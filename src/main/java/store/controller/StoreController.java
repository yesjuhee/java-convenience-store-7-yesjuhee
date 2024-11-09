package store.controller;

import java.util.function.Supplier;
import store.constants.ProductsFile;
import store.constants.PromotionsFile;
import store.model.product.Products;
import store.model.promotion.Promotions;
import store.model.purchase.Membership;
import store.model.purchase.Presents;
import store.model.purchase.Purchase;
import store.model.purchase.Purchases;
import store.view.ConfirmView;
import store.view.ErrorView;
import store.view.ProductView;
import store.view.PurchaseView;

public class StoreController {
    private final PurchaseView purchaseView = new PurchaseView();
    private final ConfirmView confirmView = new ConfirmView();
    private Purchases purchases;
    private Presents presents;
    private Membership membership;

    public void run() {
        fetchFileData();
        displayProducts();
        purchases = retryUntilSuccess(this::readPurchaseInfo);
        presents = new Presents();
        purchases.getPurchases().forEach(this::confirmPurchase);
        confirmToApplyMembership();
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
            return;
        }
        System.out.println("프로모션 적용");
    }

    private void confirmToApplyMembership() {
        boolean applyMembership = retryUntilSuccess(confirmView::confirmToApplyMembership);
        if (applyMembership) {
            membership = new Membership(purchases, presents);
            return;
        }
        membership = new Membership();
    }

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

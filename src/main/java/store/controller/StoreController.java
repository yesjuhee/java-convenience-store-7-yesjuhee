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
import store.view.ReceiptView;

public class StoreController {
    private final PurchaseView purchaseView = new PurchaseView();
    private final ConfirmView confirmView = new ConfirmView();
    private Purchases purchases;
    private Presents presents;
    private Membership membership;

    public void run() {
        fetchFileData();
        while (true) {
            displayProducts();
            purchase();
            displayReceipt();
            if (!confirmToPurchaseMore()) {
                break;
            }
        }
        Products.saveProductsData(ProductsFile.FILE_PATH);
    }

    private void fetchFileData() {
        Promotions.fetchPromotionsData(PromotionsFile.FILE_PATH);
        Products.fetchProductsData(ProductsFile.FILE_PATH);
    }

    private void displayProducts() {
        ProductView productView = new ProductView();
        productView.displayProducts(Products.getProducts());
    }

    private void purchase() {
        purchases = retryUntilSuccess(this::readPurchaseInfo);
        presents = new Presents();
        purchases.getPurchases().forEach(this::confirmPurchase);
        confirmToApplyMembership();
    }

    private Purchases readPurchaseInfo() {
        String purchaseInput = purchaseView.readPurchaseInfo();
        return new Purchases(purchaseInput);
    }

    private void confirmPurchase(Purchase purchase) {
        if (purchase.canNotApplyPromotion()) {
            purchase.purchaseWithoutPromotion();
            return;
        }
        if (purchase.notEnoughPromotionQuantity()) {
            confirmPurchaseAll(purchase);
//            purchase.purchaseWithPromotion();
            return;
        }
//        confirmAddPromotionProduct(purchase); 4.3 프로모션 추가
    }

    private void confirmPurchaseAll(Purchase purchase) {
        boolean purchaseAll = confirmView.confirmToPurchaseAll(purchase.getProductName(),
                purchase.calculateNonPromotionAmount());
        if (!purchaseAll) {
            purchase.excludeNonPromotedAmount();
        }
    }

    private void confirmAddPromotionProduct(Purchase purchase) {
    }


    private void confirmToApplyMembership() {
        boolean applyMembership = retryUntilSuccess(confirmView::confirmToApplyMembership);
        if (applyMembership) {
            membership = new Membership(purchases, presents);
            return;
        }
        membership = new Membership();
    }

    private void displayReceipt() {
        ReceiptView receiptView = new ReceiptView();
        receiptView.displayReceipt(purchases, presents, membership);
    }

    private boolean confirmToPurchaseMore() {
        return retryUntilSuccess(confirmView::confirmToPurchaseMore);
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

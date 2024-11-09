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
        do {
            initializePresents();
            displayProducts();
            purchase();
            confirmToApplyMembership();
            displayReceipt();
        } while (confirmToPurchaseMore());
        Products.saveProductsData(ProductsFile.FILE_PATH);
    }

    private void initializePresents() {
        presents = new Presents();
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
        for (Purchase purchase : purchases.getPurchases()) {
            if (purchase.canNotApplyPromotion()) {
                purchase.purchaseWithoutPromotion();
                continue;
            }
            confirmPurchaseAmount(purchase);
            purchase.purchaseWithPromotion();
            presents.addPresent(purchase);
        }
    }

    private Purchases readPurchaseInfo() {
        String purchaseInput = purchaseView.readPurchaseInfo();
        return new Purchases(purchaseInput);
    }

    private void confirmPurchaseAmount(Purchase purchase) {
        if (purchase.notEnoughPromotionQuantity()) {
            confirmPurchaseAll(purchase);
            return;
        }
        if (purchase.canGetMoreFreeProduct() && purchase.purchaseAmountLessThanPromotionQuantity()) {
            confirmGetMoreFreeProduct(purchase);
        }
    }

    private void confirmPurchaseAll(Purchase purchase) {
        boolean purchaseAll = retryUntilSuccess(() ->
                confirmView.confirmToPurchaseAll(purchase.getProductName(),
                        purchase.calculateAmountWithoutPromotion()));
        if (!purchaseAll) {
            purchase.excludeNonPromotedAmount();
        }
    }

    private void confirmGetMoreFreeProduct(Purchase purchase) {
        boolean getMoreProduct = retryUntilSuccess(
                () -> confirmView.confirmToGetMoreProduct(purchase.getProductName()));
        if (getMoreProduct) {
            purchase.addOneFreeProduct();
        }
    }


    private void confirmToApplyMembership() {
        boolean applyMembership = retryUntilSuccess(confirmView::confirmToApplyMembership);
        if (applyMembership) {
            membership = new Membership(purchases);
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

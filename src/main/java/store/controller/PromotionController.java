package store.controller;

import store.model.purchase.Presents;
import store.model.purchase.Purchase;
import store.model.purchase.Purchases;

public class PromotionController extends StoreController {
    public Presents confirmPromotionApply(Purchases purchases) {
        Presents presents = new Presents();
        purchases.getPurchases().forEach(purchase -> handlePromotion(purchase, presents));
        return presents;
    }

    private void handlePromotion(Purchase purchase, Presents presents) {
        if (purchase.canNotApplyPromotion()) {
            purchase.purchaseWithoutPromotion();
            return;
        }
        applyPromotion(purchase, presents);
    }

    private void applyPromotion(Purchase purchase, Presents presents) {
        confirmPurchaseAmount(purchase);
        purchase.purchaseWithPromotion();
        presents.addPresent(purchase);
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
        String productName = purchase.getProductName();
        int amountWithoutPromotion = purchase.calculateAmountWithoutPromotion();
        boolean purchaseAll = retryUntilSuccess(
                () -> confirmView.confirmToPurchaseAll(productName, amountWithoutPromotion));
        if (!purchaseAll) {
            purchase.excludeNonPromotedAmount();
        }
    }

    private void confirmGetMoreFreeProduct(Purchase purchase) {
        String productName = purchase.getProductName();
        boolean getMoreProduct = retryUntilSuccess(() -> confirmView.confirmToGetMoreProduct(productName));
        if (getMoreProduct) {
            purchase.addOneFreeProduct();
        }
    }
}

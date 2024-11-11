package store.controller;

import store.model.purchase.Purchases;

public class PurchaseController extends StoreController {
    public Purchases readPurchaseInfo() {
        return retryUntilSuccess(() -> {
            String purchaseInput = purchaseView.readPurchaseInfo();
            return new Purchases(purchaseInput);
        });
    }
}

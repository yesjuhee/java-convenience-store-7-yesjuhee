package store.view;

import store.constants.OutputMessage;
import store.model.purchase.Membership;
import store.model.purchase.Present;
import store.model.purchase.Presents;
import store.model.purchase.Purchase;
import store.model.purchase.Purchases;

public class ReceiptView {
    public void displayReceipt(Purchases purchases, Presents presents, Membership membership) {
        System.out.printf(OutputMessage.RECEIPT_HEADER);
        displayPurchases(purchases);
        displayPresents(presents);
    }

    private void displayPurchases(Purchases purchases) {
        System.out.printf(OutputMessage.RECEIPT_PURCHASE_HEADER);
        purchases.getPurchases().forEach(this::displayPurchase);
    }

    private void displayPurchase(Purchase purchase) {
        String productName = purchase.getProductName();
        int amount = purchase.getAmount();
        int totalPrice = purchase.getTotalPurchasePrice();
        System.out.printf(OutputMessage.RECEIPT_PURCHASE_BODY, productName, amount, totalPrice);
    }

    private void displayPresents(Presents presents) {
        System.out.printf(OutputMessage.RECEIPT_PRESENT_HEADER);
        presents.getPresents().forEach(this::displayPresent);
    }

    private void displayPresent(Present present) {
        String productName = present.getProductName();
        int amount = present.getAmount();
        System.out.printf(OutputMessage.RECEIPT_PRESENT_BODY, productName, amount);
    }

    private void displayPrice(Purchases purchases, Presents presents, Membership membership) {
    }
}

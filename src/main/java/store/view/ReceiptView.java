package store.view;

import store.constants.OutputMessageDeprecated;
import store.model.purchase.Membership;
import store.model.purchase.Present;
import store.model.purchase.Presents;
import store.model.purchase.Purchase;
import store.model.purchase.Purchases;

public class ReceiptView {
    public void displayReceipt(Purchases purchases, Presents presents, Membership membership) {
        System.out.printf(OutputMessageDeprecated.RECEIPT_HEADER);
        displayPurchases(purchases);
        displayPresents(presents);
        displayPrice(purchases, presents, membership);
    }

    private void displayPurchases(Purchases purchases) {
        System.out.printf(OutputMessageDeprecated.RECEIPT_PURCHASE_HEADER);
        purchases.getPurchases().forEach(this::displayPurchase);
    }

    private void displayPurchase(Purchase purchase) {
        String productName = purchase.getProductName();
        int amount = purchase.getAmount();
        int totalPrice = purchase.getTotalPurchasePrice();
        System.out.printf(OutputMessageDeprecated.RECEIPT_PURCHASE_BODY, productName, amount, totalPrice);
    }

    private void displayPresents(Presents presents) {
        System.out.printf(OutputMessageDeprecated.RECEIPT_PRESENT_HEADER);
        presents.getPresents().forEach(this::displayPresent);
    }

    private void displayPresent(Present present) {
        String productName = present.getProductName();
        int amount = present.getAmount();
        System.out.printf(OutputMessageDeprecated.RECEIPT_PRESENT_BODY, productName, amount);
    }

    private void displayPrice(Purchases purchases, Presents presents, Membership membership) {
        int totalAmount = purchases.calculateTotalAmount();
        int totalPrice = purchases.calculateTotalPrice();
        int promotionDiscount = presents.calculatePromotionDiscount();
        int membershipDiscount = membership.getDiscount();
        System.out.printf(OutputMessageDeprecated.RECEIPT_LINE);
        System.out.printf(OutputMessageDeprecated.RECEIPT_TOTAL_PRICE, totalAmount,
                totalPrice);
        System.out.printf(OutputMessageDeprecated.RECEIPT_PROMOTION_DISCOUNT, promotionDiscount);
        System.out.printf(OutputMessageDeprecated.RECEIPT_MEMBERSHIP_DISCOUNT, membershipDiscount);
        System.out.printf(OutputMessageDeprecated.RECEIPT_FINAL_PRICE,
                totalPrice - promotionDiscount - membershipDiscount);
    }
}

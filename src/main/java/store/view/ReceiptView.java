package store.view;

import store.constants.OutputMessage;
import store.model.purchase.Membership;
import store.model.purchase.Present;
import store.model.purchase.Presents;
import store.model.purchase.Purchase;
import store.model.purchase.Purchases;

public class ReceiptView {
    public void displayReceipt(final Purchases purchases, final Presents presents, final Membership membership) {
        System.out.printf(OutputMessage.RECEIPT_HEADER.getFormatMessage());
        displayPurchases(purchases);
        displayPresents(presents);
        System.out.printf(OutputMessage.RECEIPT_LINE.getFormatMessage());
        displayPrice(purchases, presents, membership);
    }

    private void displayPurchases(final Purchases purchases) {
        System.out.printf(OutputMessage.RECEIPT_PURCHASE_HEADER.getFormatMessage());
        purchases.getPurchases().forEach(this::displayPurchase);
    }

    private void displayPresents(final Presents presents) {
        System.out.printf(OutputMessage.RECEIPT_PRESENT_HEADER.getFormatMessage());
        presents.getPresents().forEach(this::displayPresent);
    }

    private void displayPurchase(final Purchase purchase) {
        String productName = purchase.getProductName();
        int amount = purchase.getAmount();
        int totalPrice = purchase.calculateTotalPurchasePrice();
        System.out.printf(OutputMessage.RECEIPT_PURCHASE_BODY.getFormatMessage(productName, amount, totalPrice));
    }

    private void displayPresent(final Present present) {
        String productName = present.getProductName();
        int amount = present.getAmount();
        System.out.printf(OutputMessage.RECEIPT_PRESENT_BODY.getFormatMessage(productName, amount));
    }

    private void displayPrice(final Purchases purchases, final Presents presents, final Membership membership) {
        int totalAmount = purchases.calculateTotalAmount();
        int totalPrice = purchases.calculateTotalPrice();
        int promotionDiscount = presents.calculatePromotionDiscount();
        int membershipDiscount = membership.getDiscount();
        System.out.printf(OutputMessage.RECEIPT_TOTAL_PRICE.getFormatMessage(totalAmount, totalPrice));
        System.out.printf(OutputMessage.RECEIPT_PROMOTION_DISCOUNT.getFormatMessage(promotionDiscount));
        System.out.printf(OutputMessage.RECEIPT_MEMBERSHIP_DISCOUNT.getFormatMessage(membershipDiscount));
        System.out.printf(OutputMessage.RECEIPT_FINAL_PRICE.getFormatMessage(
                calculateMembershipDiscount(totalPrice, promotionDiscount, membershipDiscount)));
    }

    private int calculateMembershipDiscount(final int totalPrice, final int promotionDiscount,
                                            final int membershipDiscount) {
        return totalPrice - promotionDiscount - membershipDiscount;
    }
}

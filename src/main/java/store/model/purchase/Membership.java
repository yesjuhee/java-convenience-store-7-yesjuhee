package store.model.purchase;

public class Membership {
    private static final double DISCOUNT_PERCENTAGE = 0.3;
    private static final int MAXIMUM_DISCOUNT = 8000;
    private final int discount;

    public Membership(final Purchases purchases) {
        int priceWithoutDiscount = calculatePriceWithoutDiscount(purchases);
        discount = membershipDiscount(priceWithoutDiscount);
    }

    public Membership() {
        this.discount = 0;
    }

    private int membershipDiscount(final int price) {
        int discountPrice = (int) (price * DISCOUNT_PERCENTAGE);
        return Math.min(discountPrice, MAXIMUM_DISCOUNT);
    }

    private int calculatePriceWithoutDiscount(final Purchases purchases) {
        int totalPrice = purchases.calculateTotalPrice();
        int promotionPrice = purchases.calculatePromotionPrice();
        return totalPrice - promotionPrice;
    }

    public int getDiscount() {
        return discount;
    }
}

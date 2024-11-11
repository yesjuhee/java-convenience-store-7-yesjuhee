package store.model.purchase;

public class Membership {
    private static final double DISCOUNT_PERCENTAGE = 0.3;
    private static final int MAXIMUM_DISCOUNT = 8000;
    private final int discount;

    private Membership(int discount) {
        this.discount = discount;
    }

    public static Membership of(final Purchases purchases) {
        int priceWithoutPromotion = calculatePriceWithoutPromotion(purchases);
        int discount = calculateMembershipDiscount(priceWithoutPromotion);
        return new Membership(discount);
    }

    public static Membership notApplied() {
        return new Membership(0);
    }

    private static int calculatePriceWithoutPromotion(final Purchases purchases) {
        int totalPrice = purchases.calculateTotalPrice();
        int promotionPrice = purchases.calculatePromotionPrice();
        return totalPrice - promotionPrice;
    }

    private static int calculateMembershipDiscount(final int price) {
        int discountPrice = (int) (price * DISCOUNT_PERCENTAGE);
        return Math.min(discountPrice, MAXIMUM_DISCOUNT);
    }

    public int getDiscount() {
        return discount;
    }
}

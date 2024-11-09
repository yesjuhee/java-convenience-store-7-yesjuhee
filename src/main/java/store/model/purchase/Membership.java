package store.model.purchase;

public class Membership {
    private static final double DISCOUNT_PERCENTAGE = 0.3;
    private static final int MAXIMUM_DISCOUNT = 8000;
    private final int discount;

    public Membership(final Purchases purchases, final Presents presents) {
        int priceWithoutDiscount = calculatePriceWithoutDiscount(purchases, presents);
        discount = membershipDiscount(priceWithoutDiscount);
    }

    public Membership() {
        this.discount = 0;
    }

    private int membershipDiscount(final int price) {
        int discountPrice = (int) (price * DISCOUNT_PERCENTAGE);
        return Math.min(discountPrice, MAXIMUM_DISCOUNT);
    }

    private int calculatePriceWithoutDiscount(final Purchases purchases, final Presents presents) {
        int totalPrice = purchases.calculateTotalPrice();
        int promotionDiscount = presents.calculatePromotionDiscount();
        return totalPrice - promotionDiscount;
    }

    public int getDiscount() {
        return discount;
    }
}

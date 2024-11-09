package store.model.product;

import store.constants.ErrorMessage;
import store.model.promotion.Promotion;
import store.model.promotion.Promotions;

public class Product {
    private final String name;
    private final Price price;
    private Quantity baseQuantity = new Quantity();
    private Quantity promotionQuantity = new Quantity();
    private Promotion promotion;

    public Product(final ProductData productData) {
        this.name = productData.getName();
        this.price = new Price(productData.getPrice());
        updateQuantity(productData);
    }

    public void updateQuantity(final ProductData productData) {
        if (productData.hasPromotion()) {
            this.promotion = Promotions.getPromotionByName(productData.getPromotion());
            this.promotionQuantity = new Quantity(productData.getQuantify());
            return;
        }
        this.baseQuantity = new Quantity(productData.getQuantify());
    }

    public void validatePurchaseAmount(final int purchaseAmount) {
        if (baseQuantity.getQuantity() + promotionQuantity.getQuantity() < purchaseAmount) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_STOCK);
        }
    }

    public boolean isInPromotion() {
        return (hasPromotion() && promotion.inPeriod());
    }

    public boolean hasPromotion() {
        return this.promotion != null;
    }

    public void reduceQuantityWithoutPromotion(int amount) {
        int difference = getBaseQuantity() - amount;
        if (difference > 0) {
            baseQuantity.subtract(amount);
            return;
        }
        baseQuantity.subtract(getBaseQuantity());
        promotionQuantity.subtract(difference * (-1));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price.getPrice();
    }

    public int getBaseQuantity() {
        return baseQuantity.getQuantity();
    }

    public int getPromotionQuantity() {
        return promotionQuantity.getQuantity();
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public int getPromotionUnit() {
        return promotion.getPromotionUnit();
    }
}

package store.model.product;

import store.constants.ErrorMessage;
import store.model.promotion.Promotion;
import store.model.promotion.PromotionDatabase;

public class Product {
    private final String name;
    private final int price;
    private Quantity baseQuantity = new Quantity();
    private Quantity promotionQuantity = new Quantity();
    private Promotion promotion;

    public Product(final ProductData productData) {
        this.name = productData.getName();
        this.price = productData.getPrice();
        updateQuantity(productData);
    }

    public void updateQuantity(final ProductData productData) {
        if (productData.hasPromotion()) {
            this.promotion = PromotionDatabase.getPromotionByName(productData.getPromotion());
            this.promotionQuantity = new Quantity(productData.getQuantify());
            return;
        }
        this.baseQuantity = new Quantity(productData.getQuantify());
    }

    public void validatePurchaseAmount(final int purchaseAmount) {
        if (baseQuantity.getQuantity() + promotionQuantity.getQuantity() < purchaseAmount) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_STOCK.getFormatMessage());
        }
    }

    public boolean isNotInPromotion() {
        return (!hasPromotion() || !promotion.inPeriod());
    }

    public boolean hasPromotion() {
        return this.promotion != null;
    }

    public void reduceQuantityWithoutPromotion(int amount) {
        if (getBaseQuantity() > amount) {
            baseQuantity.subtract(amount);
            return;
        }
        int difference = amount - getBaseQuantity();
        baseQuantity.subtract(getBaseQuantity());
        promotionQuantity.subtract(difference);
    }

    public void reduceQuantityWithPromotion(int amount) {
        if (getPromotionQuantity() > amount) {
            promotionQuantity.subtract(amount);
            return;
        }
        int difference = amount - getPromotionQuantity();
        promotionQuantity.subtract(getPromotionQuantity());
        baseQuantity.subtract(difference);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
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

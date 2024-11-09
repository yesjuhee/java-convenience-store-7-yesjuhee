package store.model.purchase;

import store.constants.ErrorMessage;
import store.model.product.Product;
import store.model.product.Products;
import store.model.promotion.Promotion;

public class Purchase {
    private final Product product;
    private int amount;
    private int amountWithPromotion;

    public Purchase(final String productName, final int amount) {
        validateProductExist(productName);
        this.product = Products.getProductByName(productName);
        product.validatePurchaseAmount(amount);
        this.amount = amount;
        this.amountWithPromotion = initializeAmountWithPromotion();
    }

    private int initializeAmountWithPromotion() {
        if (!product.isInPromotion()) {
            return 0;
        }
        int promotionQuantity = product.getPromotionQuantity();
        int promotionUnit = product.getPromotionUnit();
        int amountWithPromotion = 0;
        while (canAddPromotionUnit(amountWithPromotion, promotionUnit, promotionQuantity)) {
            amountWithPromotion += promotionUnit;
        }
        return amountWithPromotion;
    }

    private boolean canAddPromotionUnit(final int amountWithPromotion, final int promotionUnit,
                                        final int promotionQuantity) {
        return amountWithPromotion + promotionUnit <= this.amount
                && amountWithPromotion + promotionUnit <= promotionQuantity;
    }

    private void validateProductExist(final String productName) {
        if (!Products.hasProduct(productName)) {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_NOT_EXIST);
        }
    }

    public boolean canNotApplyPromotion() {
        return !product.isInPromotion();
    }

    public void purchaseWithoutPromotion() {
        product.reduceQuantityWithoutPromotion(amount);
        Products.switchProduct(product);
    }

    public void purchaseWithPromotion() {
        product.reduceQuantityWithPromotion(amount);
        Products.switchProduct(product);
    }

    public boolean notEnoughPromotionQuantity() {
        return amount > product.getPromotionQuantity();
    }

    public int calculateAmountWithoutPromotion() {
        return amount - amountWithPromotion;
    }

    public boolean purchaseAmountLessThanPromotionQuantity() {
        return amount < product.getPromotionQuantity();
    }

    public void excludeNonPromotedAmount() {
        amount = amountWithPromotion;
    }

    public void addOneFreeProduct() {
        amount += 1;
        amountWithPromotion = initializeAmountWithPromotion();
    }

    public boolean canGetMoreFreeProduct() {
        Promotion promotion = product.getPromotion();
        return promotion.canGetMoreFreeProduct(amount);
    }

    public String getProductName() {
        return product.getName();
    }

    public int getTotalPurchasePrice() {
        return amount * product.getPrice();
    }

    public int getAmount() {
        return amount;
    }

    public int calculatePresentAmount() {
        return amountWithPromotion / product.getPromotionUnit();
    }
}

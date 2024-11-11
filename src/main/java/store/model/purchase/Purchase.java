package store.model.purchase;

import store.constants.ErrorMessage;
import store.model.product.Product;
import store.model.product.ProductDatabase;
import store.model.promotion.Promotion;

public class Purchase {
    private final Product product;
    private int amount;
    private int amountWithPromotion;

    public Purchase(final String productName, final int amount) {
        validateProductExist(productName);
        this.product = ProductDatabase.getProductByName(productName);
        product.validatePurchaseAmount(amount);
        this.amount = amount;
        this.amountWithPromotion = initializeAmountWithPromotion();
    }

    public void purchaseWithoutPromotion() {
        product.reduceQuantityWithoutPromotion(amount);
        ProductDatabase.updateProductDatabase(product);
    }

    public void purchaseWithPromotion() {
        product.reduceQuantityWithPromotion(amount);
        ProductDatabase.updateProductDatabase(product);
    }

    public boolean canNotApplyPromotion() {
        return product.isNotInPromotion();
    }

    public boolean notEnoughPromotionQuantity() {
        return amount > product.getPromotionQuantity();
    }

    public boolean canGetMoreFreeProduct() {
        if (amount >= product.getPromotionQuantity()) {
            return false;
        }
        Promotion promotion = product.getPromotion();
        return promotion.canGetMoreFreeProduct(amount);
    }

    public int calculateAmountWithoutPromotion() {
        return amount - amountWithPromotion;
    }

    public int calculateTotalPurchasePrice() {
        return amount * product.getPrice();
    }

    public int calculatePromotionDiscountPrice() {
        return amountWithPromotion * product.getPrice();
    }

    public int calculatePresentAmount() {
        return amountWithPromotion / product.getPromotionUnit();
    }

    public void excludeNonPromotedAmount() {
        amount = amountWithPromotion;
    }

    public void addOneFreeProduct() {
        amount += 1;
        amountWithPromotion = initializeAmountWithPromotion();
    }

    private void validateProductExist(final String productName) {
        if (!ProductDatabase.hasProduct(productName)) {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_NOT_EXIST.getFormatMessage());
        }
    }

    private int initializeAmountWithPromotion() {
        if (product.isNotInPromotion()) {
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

    public String getProductName() {
        return product.getName();
    }

    public int getAmount() {
        return amount;
    }
}

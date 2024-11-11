package store.model.purchase;

import java.util.Arrays;
import java.util.List;
import store.constants.ErrorMessage;

public class Purchases {
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";
    private static final String PRODUCT_DELIMITER = "-";
    private static final String PURCHASE_DELIMITER = ",";
    private static final String POSITIVE_INTEGER_REGEX = "\\d+";

    private final List<Purchase> purchases;

    public Purchases(final String purchasesInput) {
        List<String> splitPurchaseInput = splitInput(purchasesInput);
        splitPurchaseInput.forEach(this::validatePurchaseFormat);
        this.purchases = splitPurchaseInput.stream().map(this::parsePurchase).toList();
    }

    public int calculateTotalPrice() {
        return purchases.stream()
                .mapToInt(Purchase::calculateTotalPurchasePrice)
                .sum();
    }

    public int calculatePromotionDiscountPrice() {
        return purchases.stream()
                .mapToInt(Purchase::calculatePromotionDiscountPrice)
                .sum();
    }

    public int calculateTotalAmount() {
        return purchases.stream()
                .mapToInt(Purchase::getAmount)
                .sum();
    }

    private List<String> splitInput(final String input) {
        return Arrays.stream(input.split(PURCHASE_DELIMITER))
                .map(String::strip).toList();
    }

    private void validatePurchaseFormat(final String productInput) {
        if (!productInput.startsWith(PREFIX) || !productInput.endsWith(SUFFIX) || !productInput.contains(
                PRODUCT_DELIMITER)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_FORMAT.getFormatMessage());
        }
        int delimiterIndex = productInput.indexOf(PRODUCT_DELIMITER);
        if (productInput.indexOf(PRODUCT_DELIMITER, delimiterIndex + 1) != -1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_FORMAT.getFormatMessage());
        }
    }

    private Purchase parsePurchase(final String purchaseInput) {
        String productName = splitProductName(purchaseInput).strip();
        String purchaseAmount = splitAmountInput(purchaseInput).strip();
        validatePositiveNumber(purchaseAmount);
        return new Purchase(productName, Integer.parseInt(purchaseAmount));
    }

    private String splitProductName(final String purchaseInput) {
        return purchaseInput.substring(purchaseInput.indexOf(PREFIX) + 1, purchaseInput.indexOf(PRODUCT_DELIMITER));
    }

    private String splitAmountInput(final String purchaseInput) {
        return purchaseInput.substring(purchaseInput.indexOf(PRODUCT_DELIMITER) + 1, purchaseInput.indexOf(SUFFIX));
    }

    private void validatePositiveNumber(final String countInput) {
        if (!countInput.matches(POSITIVE_INTEGER_REGEX) || countInput.equals("0")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_FORMAT.getFormatMessage());
        }
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
}

package store.model.purchase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import store.constants.ErrorMessage;

public class Purchases {
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";
    private static final String PRODUCT_DELIMITER = "-";
    private static final String PURCHASE_DELIMITER = ",";
    private static final String POSITIVE_INTEGER_REGEX = "\\d+";

    private final List<Purchase> purchases = new ArrayList<>();

    public Purchases(final String purchasesInput) {
        List<String> splitPurchaseInput = splitInput(purchasesInput);
        for (String purchaseInput : splitPurchaseInput) {
            purchases.add(parsePurchase(purchaseInput));
        }
    }

    private List<String> splitInput(final String input) {
        return Arrays.stream(input.split(PURCHASE_DELIMITER))
                .map(String::strip).toList();
    }

    private Purchase parsePurchase(final String purchaseInput) {
        validatePurchaseFormat(purchaseInput);
        String productName = splitProductName(purchaseInput).strip();
        String amountInput = splitAmountInput(purchaseInput).strip();
        validatePositiveNumber(amountInput);
        return new Purchase(productName, Integer.parseInt(amountInput));
    }

    private void validatePurchaseFormat(final String productInput) {
        if (!productInput.startsWith(PREFIX) || !productInput.endsWith(SUFFIX) || !productInput.contains(
                PRODUCT_DELIMITER)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_FORMAT);
        }
        int delimiterIndex = productInput.indexOf(PRODUCT_DELIMITER);
        if (productInput.indexOf(PRODUCT_DELIMITER, delimiterIndex + 1) != -1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_FORMAT);
        }
    }

    private String splitProductName(final String purchaseInput) {
        return purchaseInput.substring(purchaseInput.indexOf(PREFIX) + 1, purchaseInput.indexOf(PRODUCT_DELIMITER));
    }

    private String splitAmountInput(final String purchaseInput) {
        return purchaseInput.substring(purchaseInput.indexOf(PRODUCT_DELIMITER) + 1, purchaseInput.indexOf(SUFFIX));
    }

    private void validatePositiveNumber(final String countInput) {
        if (!countInput.matches(POSITIVE_INTEGER_REGEX) || countInput.equals("0")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_FORMAT);
        }
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public int calculateTotalPrice() {
        return purchases.stream()
                .mapToInt(Purchase::getTotalPurchasePrice)
                .sum();
    }

    public int calculateTotalAmount() {
        return purchases.stream()
                .mapToInt(Purchase::getAmount)
                .sum();
    }
}

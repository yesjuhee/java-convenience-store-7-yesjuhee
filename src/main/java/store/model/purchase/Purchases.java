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
        String productName = splitProductName(purchaseInput);
        String countInput = splitCountInput(purchaseInput);
        validatePositiveNumber(countInput);
        return new Purchase(productName, Integer.parseInt(countInput));
    }

    private void validatePurchaseFormat(final String productInput) {
        if (!productInput.startsWith(PREFIX) || !productInput.endsWith(SUFFIX) || !productInput.contains(
                PRODUCT_DELIMITER)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_FORMAT);
        }
    }

    private String splitProductName(final String purchaseInput) {
        return purchaseInput.substring(purchaseInput.indexOf(PREFIX) + 1, purchaseInput.indexOf(PRODUCT_DELIMITER));
    }

    private String splitCountInput(final String purchaseInput) {
        return purchaseInput.substring(purchaseInput.indexOf(PRODUCT_DELIMITER) + 1, purchaseInput.indexOf(SUFFIX));
    }

    private void validatePositiveNumber(final String countInput) {
        if (!countInput.matches("\\d+") || countInput.equals("0")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_FORMAT);
        }
    }
}

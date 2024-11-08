package store.model.purchase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Purchases {
    private final List<Purchase> purchases = new ArrayList<>();

    public Purchases(final String purchasesInput) {
        List<String> splitPurchaseInput = splitInput(purchasesInput);
        for (String purchaseInput : splitPurchaseInput) {
            validatePurchaseInput(purchaseInput);
            String productName = splitProductName(purchaseInput);
            String countInput = splitCountInput(purchaseInput);
            validatePositiveNumber(countInput);
            purchases.add(new Purchase(productName, Integer.parseInt(countInput)));
        }
    }

    private void validatePositiveNumber(final String countInput) {
        if (!countInput.matches("\\d+") || countInput.equals("0")) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    private String splitCountInput(final String purchaseInput) {
        return purchaseInput.substring(purchaseInput.indexOf("-") + 1, purchaseInput.indexOf("]"));
    }

    private String splitProductName(final String purchaseInput) {
        return purchaseInput.substring(1, purchaseInput.indexOf("-"));
    }

    private List<String> splitInput(final String input) {
        return Arrays.stream(input.split(","))
                .map(String::strip).toList();
    }

    private void validatePurchaseInput(final String productInput) {
        if (!productInput.startsWith("[")) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
        if (!productInput.endsWith("]")) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
        if (productInput.indexOf("-") < 0) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }
}

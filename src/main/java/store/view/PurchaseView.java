package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.constants.ErrorMessage;
import store.constants.OutputMessage;

public class PurchaseView {
    private static final String YES_REGEX = "Y|y";
    private static final String NO_REGEX = "N|n";

    public String readPurchaseInfo() {
        System.out.printf(OutputMessage.INPUT_PURCHASE);
        return Console.readLine();
    }

    public boolean confirmToAddPromotionProduct(String productName, String amount) {
        System.out.printf(OutputMessage.ADD_PROMOTION_PRODUCT_CONFIRM, productName, amount);
        String reply = Console.readLine();
        return parseReply(reply);
    }

//    public boolean confirmToPayFullPrice() {
//    }
//
//    public boolean confirmToApplyMemebership() {
//    }
//
//    public boolean confirmToPurchaseMore() {
//    }

    private boolean parseReply(String replyInput) {
        if (replyInput.matches(YES_REGEX)) {
            return true;
        }
        if (replyInput.matches(NO_REGEX)) {
            return false;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT);
    }
}

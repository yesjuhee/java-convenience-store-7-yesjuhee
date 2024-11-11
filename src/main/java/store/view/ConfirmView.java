package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.constants.ErrorMessage;
import store.constants.OutputMessageDeprecated;

public class ConfirmView {
    private static final String YES_REGEX = "[Yy]";
    private static final String NO_REGEX = "[Nn]";

    public boolean confirmToPurchaseAll(final String productName, final int amount) {
        System.out.printf(OutputMessageDeprecated.PURCHASE_ALL_CONFIRM, productName, amount);
        String reply = Console.readLine();
        return parseReply(reply);
    }

    public boolean confirmToGetMoreProduct(final String productName) {
        System.out.printf(OutputMessageDeprecated.ADD_PROMOTION_PRODUCT_CONFIRM, productName);
        String reply = Console.readLine();
        return parseReply(reply);
    }

    public boolean confirmToApplyMembership() {
        System.out.printf(OutputMessageDeprecated.APPLY_MEMBERSHIP_CONFIRM);
        String reply = Console.readLine();
        return parseReply(reply);
    }

    public boolean confirmToPurchaseMore() {
        System.out.printf(OutputMessageDeprecated.PURCHASE_CONTINUE_CONFIRM);
        String reply = Console.readLine();
        return parseReply(reply);
    }

    private boolean parseReply(final String replyInput) {
        if (replyInput.matches(YES_REGEX)) {
            return true;
        }
        if (replyInput.matches(NO_REGEX)) {
            return false;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getFormatMessage());
    }
}

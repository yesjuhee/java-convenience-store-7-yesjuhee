package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.constants.ErrorMessage;
import store.constants.OutputMessage;

public class ConfirmView {
    private static final String YES_REGEX = "[Yy]";
    private static final String NO_REGEX = "[Nn]";

    public boolean confirmToPurchaseAll(final String productName, final int amount) {
        System.out.printf(OutputMessage.PURCHASE_ALL_CONFIRM.getFormatMessage(productName, amount));
        return handleYesOrNoReply();
    }

    public boolean confirmToGetMoreProduct(final String productName) {
        System.out.printf(OutputMessage.ADD_PROMOTION_PRODUCT_CONFIRM.getFormatMessage(productName));
        return handleYesOrNoReply();
    }

    public boolean confirmToApplyMembership() {
        System.out.printf(OutputMessage.APPLY_MEMBERSHIP_CONFIRM.getFormatMessage());
        return handleYesOrNoReply();
    }

    public boolean confirmToPurchaseMore() {
        System.out.printf(OutputMessage.PURCHASE_CONTINUE_CONFIRM.getFormatMessage());
        return handleYesOrNoReply();
    }

    private boolean handleYesOrNoReply() {
        String reply = Console.readLine();
        validateFormat(reply);
        return parseReply(reply);
    }

    private void validateFormat(String reply) {
        if (!reply.matches(YES_REGEX) && !reply.matches(NO_REGEX)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getFormatMessage());
        }
    }

    private boolean parseReply(final String replyInput) {
        if (replyInput.matches(YES_REGEX)) {
            return true;
        }
        return false;
    }
}

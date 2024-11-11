package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.constants.OutputMessage;

public class PurchaseView {
    public String readPurchaseInfo() {
        System.out.printf(OutputMessage.INPUT_PURCHASE.getFormatMessage());
        return Console.readLine();
    }
}

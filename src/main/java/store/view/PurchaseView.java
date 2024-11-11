package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.constants.OutputMessageDeprecated;

public class PurchaseView {
    public String readPurchaseInfo() {
        System.out.printf(OutputMessageDeprecated.INPUT_PURCHASE);
        return Console.readLine();
    }
}

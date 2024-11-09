package store.constants;

public class OutputMessage {
    public static final String WELCOME = "안녕하세요. W편의점입니다.%n현재 보유하고 있는 상품입니다.%n%n";

    public static final String PRODUCT = "- %s %,d원 %,d개%n";
    public static final String PRODUCT_OUT_OF_STOCK = "- %s %,d원 재고 없음%n";
    public static final String PROMOTION_PRODUCT = "- %s %,d원 %d개 %s%n";
    public static final String PROMOTION_PRODUCT_OUT_OF_STOCK = "- %s %,d원 재고 없음 %s%n";

    public static final String INPUT_PURCHASE = "%n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])%n";

    public static final String ADD_PROMOTION_PRODUCT_CONFIRM = "%n현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    public static final String PAY_FULL_PRICE_CONFIRM = "%n현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    public static final String APPLY_MEMBERSHIP_CONFIRM = "%n멤버십 할인을 받으시겠습니까? (Y/N)%n";
    public static final String PURCHASE_CONTINUE_CONFIRM = "%n감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)%n";

    public static final String RECEIPT_HEADER = "%n================W 편의점================%n";
    public static final String RECEIPT_PURCHASE_HEADER = String.format("%-18s%-8s%8s%n", "상품명", "수량", "금액");
    public static final String RECEIPT_PURCHASE_BODY = "%-18s%,-8d%,8d%n";
    public static final String RECEIPT_PRESENT_HEADER = "=============증\t정===============%n";
    public static final String RECEIPT_PRESENT_BODY = "%-18s %,-18d%n";
    public static final String RECEIPT_LINE = "====================================%n"; // 36
//    public static final String RECEIPT_TOTAL_PRICE = String.format("%-18s %,-8d %,8d%n", "총구매액");
//    public static final String RECEIPT_PROMOTION_DISCOUNT = String.format("%-18s %,18d%n", "행사할인");
//    public static final String RECEIPT_MEMBERSHIP_DISCOUNT = String.format("%-18s %,18d%n", "멤버십할인");
//    public static final String RECEIPT_FINAL_PRICE = String.format("%-18s %,18d%n", "내실돈");
}

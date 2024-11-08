package store.model.promotion;

public class PromotionFormat {
    private final int buy;
    private final int get;

    public PromotionFormat(String buy, String get) {
        this.buy = Integer.parseInt(buy);
        this.get = Integer.parseInt(get);
    }
}

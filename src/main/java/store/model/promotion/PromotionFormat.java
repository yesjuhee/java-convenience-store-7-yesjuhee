package store.model.promotion;

public class PromotionFormat {


    public PromotionFormat(String buy, String get) {
        this.buy = Integer.parseInt(buy);
        this.get = Integer.parseInt(get);
    }

    public int getBuyPlusGet() {
        return buy + get;
    }

    public int getBuy() {
        return buy;
    }
}

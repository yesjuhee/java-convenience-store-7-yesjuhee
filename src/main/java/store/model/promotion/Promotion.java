package store.model.promotion;

public class Promotion {
    private final String name;
    private final int buy;
    private final int get;
    private final Period period;

    public Promotion(final PromotionData promotionData) {
        this.name = promotionData.getName();
        this.buy = promotionData.getBuy();
        this.get = promotionData.getGet();
        this.period = new Period(promotionData.getStartDate(), promotionData.getEndDate());
    }

    public boolean canGetMoreFreeProduct(int purchaseAmount) {
        return (purchaseAmount % (buy + get)) == buy;
    }

    public int getPromotionUnit() {
        return buy + get;
    }

    public boolean inPeriod() {
        return period.isIn();
    }

    public String getName() {
        return name;
    }
}

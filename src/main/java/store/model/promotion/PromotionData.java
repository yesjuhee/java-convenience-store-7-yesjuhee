package store.model.promotion;

import java.util.List;
import store.constants.PromotionsFile;

public class PromotionData {
    private final String name;
    private final String buy;
    private final String get;
    private final String startDate;
    private final String endDate;

    public PromotionData(final List<String> promotionData) {
        this.name = promotionData.get(PromotionsFile.NAME_COLUMN_NUM);
        this.buy = promotionData.get(PromotionsFile.BUY_COLUMN_NUM);
        this.get = promotionData.get(PromotionsFile.GET_COLUMN_NUM);
        this.startDate = promotionData.get(PromotionsFile.START_DATE_COLUMN_NUM);
        this.endDate = promotionData.get(PromotionsFile.END_DATE_COLUMN_NUM);
    }

    public String getName() {
        return name;
    }

    public int getBuy() {
        return Integer.parseInt(buy);
    }

    public int getGet() {
        return Integer.parseInt(get);
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}

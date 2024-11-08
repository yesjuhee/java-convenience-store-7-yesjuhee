package store.model.promotion;

import java.util.List;
import store.constants.PromotionsFile;

public class Promotion {
    private final String name;
    private final PromotionFormat format;
    private final Period period;

    public Promotion(List<String> promotionData) {
        String name = promotionData.get(PromotionsFile.NAME_COLUMN_NUM);
        String buy = promotionData.get(PromotionsFile.BUY_COLUMN_NUM);
        String get = promotionData.get(PromotionsFile.GET_COLUMN_NUM);
        String startDate = promotionData.get(PromotionsFile.START_DATE_COLUMN_NUM);
        String endDate = promotionData.get(PromotionsFile.END_DATE_COLUMN_NUM);
        this.name = name;
        this.format = new PromotionFormat(buy, get);
        this.period = new Period(startDate, endDate);
    }

    public boolean inPeriod() {
        return period.isIn();
    }

    public String getName() {
        return name;
    }
}

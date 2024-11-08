package store.model.promotion;

import java.util.HashMap;
import java.util.List;
import store.constants.PromotionsFile;
import store.utils.FileUtils;

public class Promotions {
    private static HashMap<String, Promotion> promotions;

    public static void fetchPromotionsData(final String filePath) {
        promotions = new HashMap<>();
        List<List<String>> promotionsData = FileUtils.readCsvBody(filePath);
        for (List<String> promotionData : promotionsData) {
            Promotion promotion = new Promotion(promotionData);
            String promotionName = promotionData.get(PromotionsFile.NAME_COLUMN_NUM);
            promotions.put(promotionName, promotion);
        }
    }

    public static Promotion getPromotionByName(final String promotionName) {
        return promotions.get(promotionName);
    }
}

package store.model.promotion;

import java.util.HashMap;
import java.util.List;
import store.utils.FileUtils;

public class PromotionDatabase {
    private static HashMap<String, Promotion> promotions = new HashMap<>();

    public static void fetchPromotionsFile(final String filePath) {
        List<PromotionData> promotionsData = FileUtils.readCsvBody(filePath)
                .stream().map(PromotionData::new).toList();
        promotionsData.forEach((promotionData -> updatePromotionDatabase(promotionData.getName(), promotionData)));
    }

    public static Promotion getPromotionByName(final String promotionName) {
        return promotions.get(promotionName);
    }

    private static void updatePromotionDatabase(String key, PromotionData promotionData) {
        promotions.put(key, new Promotion(promotionData));
    }
}

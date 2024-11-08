package store.model.promotion;

import java.util.HashMap;
import java.util.List;
import store.constants.PromotionsFile;
import store.utils.FileUtils;

public class Promotions {
    private static Promotions instance;
    private HashMap<String, Promotion> promotions;

    private Promotions() {
    }

    public void fetchPromotionsData(final String filePath) {
        promotions = new HashMap<>();
        List<List<String>> promotionsData = FileUtils.readCsvBody(filePath);
        for (List<String> promotionData : promotionsData) {
            Promotion promotion = new Promotion(promotionData);
            String promotionName = promotionData.get(PromotionsFile.NAME_COLUMN_NUM);
            promotions.put(promotionName, promotion);
        }
    }

    public Promotion getPromotionByName(final String promotionName) {
        return promotions.get(promotionName);
    }

    public static Promotions getInstance() {
        if (instance == null) {
            instance = new Promotions();
        }
        return instance;
    }
}

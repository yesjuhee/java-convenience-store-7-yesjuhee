package store.model.promotion;

import java.util.HashMap;
import java.util.List;
import store.constants.PromotionsFile;
import store.utils.FileUtils;

public class Promotions {
    private static Promotions instance;
    private final HashMap<String, Promotion> promotions;

    private Promotions() {
        this.promotions = new HashMap<>();
        fetchPromotionsData();
    }

    private void fetchPromotionsData() {
        List<List<String>> promotionsData = FileUtils.readCsvBody(PromotionsFile.FILE_PATH);
        for (List<String> promotionData : promotionsData) {
            Promotion promotion = new Promotion(promotionData);
            String promotionName = promotionData.get(PromotionsFile.NAME_COLUMN_NUM);
            promotions.put(promotionName, promotion);
        }
    }

    public Promotion getPromotionByName(String promotionName) {
        return promotions.get(promotionName);
    }

    public static Promotions getInstance() {
        if (instance == null) {
            instance = new Promotions();
        }
        return instance;
    }
}

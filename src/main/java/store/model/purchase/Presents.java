package store.model.purchase;

import java.util.ArrayList;
import java.util.List;

public class Presents {
    private final List<Present> presents = new ArrayList<>();

    public int calculatePromotionDiscount() {
        return presents.stream().mapToInt((present) -> present.getTotalPresentPrice()).sum();
    }

    public List<Present> getPresents() {
        return presents;
    }

    public void addPresent(Purchase purchase) {
        presents.add(new Present(purchase));
    }
}

package store.model.purchase;

import java.util.ArrayList;
import java.util.List;

public class Presents {
    private final List<Present> presents = new ArrayList<>();

    public boolean hasPresent() {
        return !presents.isEmpty();
    }
}

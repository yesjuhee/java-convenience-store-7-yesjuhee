package store.model.promotion;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(String startDate, String endDate) {
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    public boolean isIn() {
        LocalDateTime nowDateTime = DateTimes.now();
        LocalDate now = nowDateTime.toLocalDate();
        if (now.isEqual(startDate)
                || now.isEqual(endDate)
                || (now.isAfter(startDate) && now.isBefore(endDate))) {
            return true;
        }
        return false;
    }
}

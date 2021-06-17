package model;

import java.util.ArrayList;
import java.util.List;

public enum ListingStatusType {
    ACTIVE(1),
    SCHEDULED(2),
    CANCELLED(3);


    private final Integer id;

    ListingStatusType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static List<Integer> getIdList() {
        List<Integer> idList = new ArrayList<>();

        for (ListingStatusType element : ListingStatusType.values()) {
            idList.add(element.getId());
        }
        return idList;
    }
}

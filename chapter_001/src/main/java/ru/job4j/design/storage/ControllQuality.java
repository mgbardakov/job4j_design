package ru.job4j.design.storage;

import java.util.*;

/**
 * class to control distribution of products
 * @author mbardakov
 * @since 24.03.2020
 */
public class ControllQuality {
    private Map<String, Storage> storages;

    public Map<String, Storage> getStorages() {
        return storages;
    }

    public ControllQuality() {
        this.storages = new HashMap<>();
        storages.put("warehouse", new Warehouse());
        storages.put("shop", new Shop());
        storages.put("trash", new Trash());
    }

    /**
     * sorts food to different storages
     * @param food - food object
     */
    public void sort(Food food) {
        var quality = percentQuality(food);
        if (quality > 75.0 && quality < 100.0) {
            food.setDiscount(30.0);
        }
        for (Map.Entry<String, Storage> s : storages.entrySet()) {
            var storage = s.getValue();
            if (storage.accept(food)) {
                storage.add(food);
            }
        }
    }

    /**
     * counts quality in percents
     * @param food - food object
     * @return - quality
     */
    public static double percentQuality(Food food) {
        var now = Calendar.getInstance().getTimeInMillis();
        var create = food.getCreateDate().getTimeInMillis();
        var expire = food.getExpireDate().getTimeInMillis();
        return ((now * 1.0 - create) / (expire - create)) * 100;
    }
}

package ru.job4j.design.storage;

import java.util.*;

/**
 * class to control distribution of products
 * @author mbardakov
 * @since 24.03.2020
 */
public class ControllQuality {
    private Map<String, Storage> storages;
    private Calendar nowDate;

    /**
     *
     * @param name of storage
     * @return 1 of 3 storages: Warehouse, Shop or Trash
     */
    public Storage getStorageByName(String name) {
        return storages.get(name);
    }

    public ControllQuality() {
        init();
        nowDate = Calendar.getInstance();
    }

    public ControllQuality(Calendar nowDate) {
        init();
        this.nowDate = nowDate;
    }

    private void init() {
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
        var quality = percentQuality(food, nowDate);
        if (quality > 75.0 && quality < 100.0) {
            food.setDiscount(30.0);
        }
        for (Map.Entry<String, Storage> s : storages.entrySet()) {
            var storage = s.getValue();
            if (storage.accept(food, nowDate)) {
                storage.add(food);
            }
        }
    }

    /**
     * counts quality in percents
     * @param food - food object
     * @return - quality
     */
    public static double percentQuality(Food food, Calendar nowDate) {
        var now = nowDate.getTimeInMillis();
        var create = food.getCreateDate().getTimeInMillis();
        var expire = food.getExpireDate().getTimeInMillis();
        return ((now * 1.0 - create) / (expire - create)) * 100;
    }

    /**
     * resorts food between storages
     */
    public void resort() {
        var tempList = new ArrayList<Food>();
        for (Map.Entry<String, Storage> s : storages.entrySet()) {
            for (Food food : s.getValue()) {
                tempList.add(food);
            }
            s.getValue().resetStore();
        }
        tempList.forEach(this::sort);
    }
}

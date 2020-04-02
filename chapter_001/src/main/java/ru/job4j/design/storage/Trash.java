package ru.job4j.design.storage;


import java.util.*;

/**
 * realization of Storage for low  quality products
 * @author mbardakov
 * @since 24.03.2020
 */
public class Trash implements Storage {
    private List<Food> foodList;

    public Trash() {
        this.foodList = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        foodList.add(food);
    }

    @Override
    public void resetStore() {
        foodList = new ArrayList<>();
    }

    @Override
    public List<Food> getList() {
        return foodList;
    }

    @Override
    public boolean accept(Food food, Calendar nowDate) {
        var quality = ControllQuality.percentQuality(food, nowDate);
        return quality >= 100.0;
    }

    @Override
    public Iterator<Food> iterator() {
        return foodList.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Trash trash = (Trash) o;
        return Objects.equals(foodList, trash.foodList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodList);
    }
}

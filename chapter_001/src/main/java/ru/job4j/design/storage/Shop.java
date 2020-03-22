package ru.job4j.design.storage;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * realization of Storage for middle  quality products
 * @author mbardakov
 * @since 24.03.2020
 */
public class Shop implements Storage {
   private List<Food> foodList;

    public Shop() {
        this.foodList = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        foodList.add(food);
    }

    @Override
    public void remove(Food food) {
         foodList.remove(food);
    }

    @Override
    public boolean accept(Food food) {
        var quality = ControllQuality.percentQuality(food);
        return quality >= 25.0 && quality < 100.0;
    }

    @Override
    public List<Food> getList() {
        return foodList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shop shop = (Shop) o;
        return Objects.equals(foodList, shop.foodList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodList);
    }
}

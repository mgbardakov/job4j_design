package ru.job4j.design.storage;

import java.util.Calendar;
import java.util.Objects;
/**
 * data model for food object
 * @author mbardakov
 * @since 24.03.2020
 */
public class Food {
    private String name;
    private Calendar expireDate;
    private Calendar createDate;
    private double price;
    private double discount;


    public Food(String name, Calendar expireDate, Calendar createDate, double price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        discount = 0;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
        this.price = 100 - (this.price * discount / 100);
    }

    public Calendar getExpireDate() {
        return expireDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0
                && Double.compare(food.discount, discount) == 0
                && Objects.equals(name, food.name)
                && Objects.equals(expireDate, food.expireDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expireDate, createDate, price, discount);
    }
}

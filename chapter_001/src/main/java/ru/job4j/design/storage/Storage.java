package ru.job4j.design.storage;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
/**
 * Storage interface
 * @author mbardakov
 * @since 24.03.2020
 */
public interface Storage extends  Iterable<Food> {
    /**
     * adds food object to storage
     * @param food - food object
     */
    void add(Food food);

    /**
     * resets Storage
     */
    void resetStore();

    /**
     * method check if food object satisfy conditions
     * @param food - food object
     * @return - satisfy / not satisfy
     */
    boolean accept(Food food, Calendar nowDate);
    /**
     * returns list of food objects in storage
     * @author mbardakov
     * @since 24.03.2020
     */
    List<Food> getList();




}

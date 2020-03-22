package ru.job4j.design.storage;

import java.util.List;
/**
 * Storage interface
 * @author mbardakov
 * @since 24.03.2020
 */
public interface Storage {
    /**
     * adds food object to storage
     * @param food - food object
     */
    void add(Food food);

    /**
     * removes food object from storage
     * @param food - food object
     */
    void remove(Food food);

    /**
     * method check if food object satisfy conditions
     * @param food - food object
     * @return - satisfy / not satisfy
     */
    boolean accept(Food food);
    /**
     * returns list of food objects in storage
     * @author mbardakov
     * @since 24.03.2020
     */
    List<Food> getList();




}

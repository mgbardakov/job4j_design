package ru.job4j.design.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Storage class for employees
 * @author mbardakov
 * @since 18.03.2020
 */
public class Store {
    protected final List<Employer> employers = new ArrayList<>();
    private Filter filter;

    public Store(Filter filter) {
        this.filter = filter;
    }

    public Store() {
        this.filter = new StandartFilter();
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    /**
     * add employee into Store
     * @param em - employer
     */
    public void add(Employer em) {
        employers.add(em);
    }
    /**
     * find employees by filter
     * @param condition - condition for filtering
     * @return list of employees
     */
    public List<Employer> findBy(Predicate<Employer> condition) {
        return filter.findBy(employers, condition);
    }

}

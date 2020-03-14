package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * interface for generating reports
 * @author mbardakov
 * @since  18.03.2020
 */
public interface ReportGenerator {
    /**
     *
     * @return storage
     */
    Store getStore();

    /**
     *
     * @param filter - condition for filtering employe
     * @return report
     */
    String generate(Predicate<Employer> filter);
}

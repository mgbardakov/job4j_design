package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Default realization for Filter interface
 * @author mbardakov
 * @since 18.03.2020
 */
public class StandartFilter implements Filter {
    @Override
    public List<Employer> findBy(List<Employer> list, Predicate<Employer> filter) {
        return list.stream().filter(filter).collect(Collectors.toList());
    }
}

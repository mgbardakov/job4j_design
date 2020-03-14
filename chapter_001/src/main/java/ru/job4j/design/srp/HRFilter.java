package ru.job4j.design.srp;


import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Filter for HR
 * @author mbardakov
 * @since 14.03.2020
 */
public class HRFilter implements Filter {
    @Override
    public List<Employer> findBy(List<Employer> employers, Predicate<Employer> filter) {
        return employers.stream().filter(filter).sorted((em1, em2) -> Double.compare(em2.getSalary(), em1.getSalary()))
                    .collect(Collectors.toList());
    }
}

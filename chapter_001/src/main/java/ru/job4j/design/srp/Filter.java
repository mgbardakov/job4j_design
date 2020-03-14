package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public interface Filter {
    List<Employer> findBy(List<Employer> list, Predicate<Employer> filter);
}

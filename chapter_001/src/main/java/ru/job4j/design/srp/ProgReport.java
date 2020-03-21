package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Class is making report for programmers
 * @author mbardakov
 * @since 14.03.2020
 */
public class ProgReport implements ReportGenerator {

    private ReportGenerator generator;

    public ProgReport(ReportGenerator generator) {
        this.generator = generator;
    }

    public ProgReport(Store store) {
        this(new ReportEngine(store));
    }

    @Override
    public Store getStore() {
        return generator.getStore();
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        return new HTMLReport(generator).generate(filter);
    }
}

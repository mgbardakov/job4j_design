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
        String original = generator.generate(filter);
        StringBuilder sb = new StringBuilder();
        sb.append("<!doctype html><html><head><title>report</title></head><body><h1>Отчёт по сотрудникам</h1><p>")
                .append(System.lineSeparator()).append(original).append("</body></html>");
        return sb.toString();
    }
}

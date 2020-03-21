package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * decorator class for making reports in HTML
 * @author mbardakov
 * @since 21.03.2020
 */
public class HTMLReport implements ReportGenerator {
     ReportGenerator generator;

    public HTMLReport(ReportGenerator generator) {
        this.generator = generator;
    }

    @Override
    public Store getStore() {
        return generator.getStore();
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        var original = generator.generate(filter);
        var sb = new StringBuilder();
        sb.append("<!doctype html><html><head><title>report</title></head><body><h1>Отчёт по сотрудникам</h1><p>")
                .append(System.lineSeparator()).append(original).append("</body></html>");
        return sb.toString();
    }
}

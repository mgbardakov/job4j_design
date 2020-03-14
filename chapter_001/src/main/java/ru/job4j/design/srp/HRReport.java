package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Class is making report for HR
 * @author mbardakov
 * @since 14.03.2020
 */
public class HRReport implements ReportGenerator {

    private ReportGenerator generator;

    public HRReport(ReportGenerator generator) {
        this.generator = generator;
    }

    public HRReport(Store store) {
        this(new ReportEngine(store));
        store.setFilter(new HRFilter());
    }

    @Override
    public Store getStore() {
        return generator.getStore();
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        text.append(System.lineSeparator());
        for (Employer employer : getStore().findBy(filter)) {
            text.append(employer.getName()).append(";")
                    .append(employer.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

package ru.job4j.design.srp;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * class is making reports
 * @author mbardakov
 * @since 14.03.2020
 */
public class ReportEngine implements ReportGenerator {
    private Store store;

    public Store getStore() {
        return store;
    }

    public ReportEngine(Store store) {
        this.store = store;
    }

    /**
     * generate method for internal usage
     * @param filter - filters employees
     * @param transform - transforms employee's salary     *
     * @return report
     */
    public String generate(Predicate<Employer> filter, Function<Double, String> transform) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employer employer : store.findBy(filter)) {
            text.append(employer.getName()).append(";")
                    .append(employer.getHired()).append(";")
                    .append(employer.getFired()).append(";")
                    .append(transform.apply(employer.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    /**
     * generate method for public usage
     * @param filter - filters employees
     * @return report
     */
    public String generate(Predicate<Employer> filter) {
        return generate(filter, String::valueOf);
    }
}

package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Class is making report for economists
 * @author mbardakov
 * @since 14.03.2020
 */
public class EcomomistReport implements ReportGenerator {
    private ReportGenerator generator;

    public EcomomistReport(ReportGenerator generator) {
        this.generator = generator;
    }

    public EcomomistReport(Store store) {
        this(new ReportEngine(store));
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employer employer : getStore().findBy(filter)) {
            text.append(employer.getName()).append(";")
                    .append(employer.getHired()).append(";")
                    .append(employer.getFired()).append(";")
                    .append(transformSalary(employer.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    @Override
    public Store getStore() {
        return generator.getStore();
    }

    /**
     * @param salary - salary in double format
     * @return salary in string format
     */
     private static String transformSalary(double salary) {
        String textSalary = String.valueOf(salary);
        return String.format("%s р. %s коп.", textSalary.substring(0, textSalary.length() - 3),
                                              textSalary.substring(textSalary.length() - 2));
     }
}

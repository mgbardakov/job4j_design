package ru.job4j.design.srp;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    /**
     * transforms report string into list of columns
     * @param report report string
     * @return list of columns
     */
    public static List<Column> prepareReport(String report) {
        Pattern pat = Pattern.compile(";");
        var lines = report.lines().collect(Collectors.toList());
        var resultList = new ArrayList<List<String>>();
        for (String line : lines) {
            var newLine = pat.split(line);
            var element = Arrays.stream(newLine).map(String::trim).collect(Collectors.toList());
            resultList.add(element);
        }
         List<Column> columns = new ArrayList<>();
         for (int i = 0; i < resultList.get(0).size(); i++) {
            var headLine = resultList.get(0).get(i).toLowerCase();
            var elements = new ArrayList<String>();
            for (int j = 1; j < resultList.size(); j++) {
                elements.add(resultList.get(j).get(i));
            }
            columns.add(new Column(headLine, elements));
         }
         return columns;

    }


}

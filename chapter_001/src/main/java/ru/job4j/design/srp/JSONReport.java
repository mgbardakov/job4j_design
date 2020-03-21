package ru.job4j.design.srp;

import java.util.function.Predicate;
/**
 * decorator class for making reports in JSON
 * @author mbardakov
 * @since 21.03.2020
 */
public class JSONReport implements ReportGenerator {

    private ReportGenerator generator;

    public JSONReport(ReportGenerator generator) {
        this.generator = generator;
    }

    @Override
    public Store getStore() {
        return generator.getStore();
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        var columns = ReportEngine.prepareReport(generator.generate(filter));
        var text = new StringBuilder().append("{").append(System.lineSeparator())
                .append("\"employees\":[").append(System.lineSeparator());
        for (int i = 0; i < columns.get(0).getElements().size(); i++) {
            text.append("\"employee\":{");
            for (int j = 0; j < columns.size(); j++) {
                var c = columns.get(j);
                text.append(String.format("\"%s\":\"%s\"%s", c.getHeadLine(), c.getElements().get(i),
                        j == columns.size() - 1 ? "}" : ", "));
            }
            text.append(String.format("%s", i == columns.get(0).getElements().size() - 1
                         ? System.lineSeparator() + "]" : "," + System.lineSeparator()));
        }
        text.append(System.lineSeparator()).append("}");
        return text.toString();
    }
}

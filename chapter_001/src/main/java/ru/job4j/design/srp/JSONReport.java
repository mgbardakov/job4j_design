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
        var data = ReportEngine.prepareReport(generator.generate(filter));
        var text = new StringBuilder().append("{").append(System.lineSeparator())
                .append("\"employees\":[").append(System.lineSeparator());
        for (int i = 1; i < data.size(); i++) {
            text.append("\"employee\":{");
            for (int j = 0; j < data.get(i).size(); j++) {
                var c = data.get(i).get(j);
                text.append(String.format("\"%s\":\"%s\"%s", data.get(0).get(j), c,
                        j == data.get(i).size() - 1 ? "}" : ", "));
            }
            text.append(String.format("%s", i == data.size() - 1
                         ? System.lineSeparator() + "]" : "," + System.lineSeparator()));
        }
        text.append(System.lineSeparator()).append("}");
        return text.toString();
    }
}

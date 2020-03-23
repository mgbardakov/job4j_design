package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * decorator class for making reports in XML
 * @author mbardakov
 * @since 21.03.2020
 */
public class XMLReport implements ReportGenerator {

     private ReportGenerator generator;

    public XMLReport(ReportGenerator generator) {
        this.generator = generator;
    }

    @Override

    public Store getStore() {
        return generator.getStore();
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        var text = new StringBuilder().append("<?xml version=\"1.0\"?>").append(System.lineSeparator())
                .append("<report>").append(System.lineSeparator())
                .append("<employees>").append(System.lineSeparator());
        var origin = generator.generate(filter);
        var data = ReportEngine.prepareReport(origin);
        for (int i = 1; i < data.size(); i++) {
            text.append("<employee>").append(System.lineSeparator());
            for (int j = 0; j < data.get(i).size(); j++) {
                var c = data.get(i).get(j);
                text.append(String.format("<%s>%s</%s>", data.get(0).get(j), c, data.get(0).get(j)));
                text.append(System.lineSeparator());
            }
            text.append("</employee>").append(System.lineSeparator());
        }
        text.append("</employees>").append(System.lineSeparator()).append("</report>");
        return text.toString();
    }

}

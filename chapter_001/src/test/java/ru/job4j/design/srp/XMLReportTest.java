package ru.job4j.design.srp;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.Calendar;


public class XMLReportTest {
      @Test
  public void whenGeneratedXMLForEconomists() {
        Store store = new Store();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Igor", now, now, 18400.23);
        Employer worker2 = new Employer("Ivan", now, now, 22900.30);
        store.add(worker);
        store.add(worker2);
        String result = new XMLReport(new EcomomistReport(store)).generate(em -> true);
        String expected = new StringBuilder().append("<?xml version=\"1.0\"?>").append(System.lineSeparator())
                .append("<report>").append(System.lineSeparator())
                .append("<employees>").append(System.lineSeparator())
                .append("<employee>").append(System.lineSeparator())
                .append("<name>").append(worker.getName()).append("</name>")
                .append(System.lineSeparator())
                .append("<hired>").append(worker.getHired()).append("</hired>")
                .append(System.lineSeparator())
                .append("<fired>").append(worker.getFired()).append("</fired>")
                .append(System.lineSeparator())
                .append("<salary>").append("18400 р. 23 коп.").append("</salary>")
                .append(System.lineSeparator())
                .append("</employee>").append(System.lineSeparator())
                .append("<employee>").append(System.lineSeparator())
                .append("<name>").append(worker2.getName()).append("</name>")
                .append(System.lineSeparator())
                .append("<hired>").append(worker2.getHired()).append("</hired>")
                .append(System.lineSeparator())
                .append("<fired>").append(worker2.getFired()).append("</fired>")
                .append(System.lineSeparator())
                .append("<salary>").append("22900 р. 30 коп.").append("</salary>")
                .append(System.lineSeparator())
                .append("</employee>").append(System.lineSeparator())
                .append("</employees>").append(System.lineSeparator())
                .append("</report>").toString();
        assertThat(result, is(expected));
    }

    @Test
    public void whenGeneratedXMLForHR() {
        Store store = new Store(new HRFilter());
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Igor", now, now, 18400.23);
        store.add(worker);
        String result = new XMLReport(new HRReport(store)).generate(em -> true);
        String expected = new StringBuilder().append("<?xml version=\"1.0\"?>").append(System.lineSeparator())
                .append("<report>").append(System.lineSeparator())
                .append("<employees>").append(System.lineSeparator())
                .append("<employee>").append(System.lineSeparator())
                .append("<name>").append(worker.getName()).append("</name>")
                .append(System.lineSeparator())
                .append("<salary>").append(worker.getSalary()).append("</salary>")
                .append(System.lineSeparator())
                .append("</employee>").append(System.lineSeparator())
                .append("</employees>").append(System.lineSeparator())
                .append("</report>").toString();
        assertThat(result, is(expected));
    }
}

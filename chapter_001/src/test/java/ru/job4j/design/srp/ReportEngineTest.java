package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        Store store = new Store();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        store.add(worker);
        ReportGenerator generator = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(generator.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenEconomistGenerated() {
        Store store = new Store();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 13800.49);
        store.add(worker);
        ReportGenerator generator = new EcomomistReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append("13800 р. 49 коп.;")
                .append(System.lineSeparator());
        assertThat(generator.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRGenerated() {
        Store store = new Store();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 13800.49);
        Employer worker2 = new Employer("Arcady", now, now, 15000.49);
        store.add(worker);
        store.add(worker2);
        ReportGenerator generator = new HRReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(generator.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenProgrammerGenerated() {
        Store store = new Store();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        store.add(worker);
        ReportGenerator generator = new ProgReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<!doctype html><html><head><title>report</title></head><body><h1>Отчёт по сотрудникам</h1><p>")
                .append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</body></html>");
        assertThat(generator.generate(em -> true), is(expect.toString()));
    }
}

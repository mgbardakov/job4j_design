package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JSONReportTest {

    @Test
    public void whenGeneratedJsonEconomistReport() {
        Calendar now = Calendar.getInstance();
        Employer worker1 = new Employer("Ivan", now, now, 34678.97);
        Employer worker2 = new Employer("Igor", now, now, 32578.56);
        Store store = new Store();
        store.add(worker1);
        store.add(worker2);
        String result = new JSONReport(new EcomomistReport(store)).generate(em -> true);
        var sep = System.lineSeparator();
        String expected = new StringBuilder().append("{").append(sep).append("\"employees\":[").append(sep)
        .append(String.format("\"employee\":{\"name\":\"Ivan\", \"hired\":\"%s\", \"fired\":\"%s\", \"salary\":\"34678 р. 97 коп.\"},",
                worker1.getHired(), worker1.getFired())).append(sep)
         .append(String.format("\"employee\":{\"name\":\"Igor\", \"hired\":\"%s\", \"fired\":\"%s\", \"salary\":\"32578 р. 56 коп.\"}",
                worker2.getHired(), worker2.getFired())).append(sep).append("]").append(sep).append("}").toString();
        assertThat(result, is(expected));
    }
}
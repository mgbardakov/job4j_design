package ru.job4j.design.storage;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControllQualityTest {

    @Test
    public void whenSortedToWarehouse() {
        Calendar create = new GregorianCalendar(2020, Calendar.MARCH, 20);
        Calendar expire = new GregorianCalendar(2020, Calendar.JUNE, 15);
        Food cola = new Food("Product", expire, create, 100.52);
        ControllQuality controller = new ControllQuality(new GregorianCalendar(2020, Calendar.MARCH, 25));
        controller.sort(cola);
        Food result = controller.getStorageByName("warehouse").iterator().next();
        assertThat(result, is(cola));
    }

    @Test
    public void whenSortedToShop() {
        Calendar create = new GregorianCalendar(2019, Calendar.DECEMBER, 20);
        Calendar expire = new GregorianCalendar(2020, Calendar.JUNE, 15);
        Food cola = new Food("Cola", expire, create, 100.52);
        ControllQuality controller = new ControllQuality(new GregorianCalendar(2020, Calendar.MARCH, 25));
        controller.sort(cola);
        Food result = controller.getStorageByName("shop").iterator().next();
        assertThat(result, is(cola));
        assertEquals(100.52, result.getPrice(), 0.1);
    }

    @Test
    public void whenSortedToShopWithDiscount() {
        Calendar create = new GregorianCalendar(2019, Calendar.DECEMBER, 20);
        Calendar expire = new GregorianCalendar(2020, Calendar.APRIL, 10);
        Food cola = new Food("Cola", expire, create, 100.0);
        ControllQuality controller = new ControllQuality(new GregorianCalendar(2020, Calendar.MARCH, 25));
        controller.sort(cola);
        Food result = controller.getStorageByName("shop").iterator().next();
        assertThat(result, is(cola));
        assertEquals(70, result.getPrice(), 0.1);
    }

    @Test
    public void whenSortedToTrash() {
        Calendar create = new GregorianCalendar(2019, Calendar.DECEMBER, 20);
        Calendar expire = new GregorianCalendar(2020, Calendar.MARCH, 10);
        Food cola = new Food("Cola", expire, create, 100.0);
        ControllQuality controller = new ControllQuality(new GregorianCalendar(2020, Calendar.MARCH, 25));
        controller.sort(cola);
        Food result = controller.getStorageByName("trash").iterator().next();
        assertThat(result, is(cola));
    }

    @Test
    public void whenResortedToTrash() {
        Calendar create = new GregorianCalendar(2019, Calendar.DECEMBER, 20);
        Calendar expire = new GregorianCalendar(2020, Calendar.MARCH, 10);
        Food cola = new Food("Cola", expire, create, 100.0);
        ControllQuality controller = new ControllQuality(new GregorianCalendar(2020, Calendar.MARCH, 25));
        controller.getStorageByName("shop").add(cola);
        controller.resort();
        Food result = controller.getStorageByName("trash").iterator().next();
        assertThat(result, is(cola));
    }

    @Test
    public void whenResortThenNothingChanged() {
        Calendar create = new GregorianCalendar(2019, Calendar.DECEMBER, 20);
        Calendar expire = new GregorianCalendar(2020, Calendar.MARCH, 10);
        Food cola = new Food("Cola", expire, create, 100.0);
        ControllQuality controller = new ControllQuality(new GregorianCalendar(2020, Calendar.MARCH, 25));
        controller.getStorageByName("trash").add(cola);
        controller.resort();
        Food result = controller.getStorageByName("trash").iterator().next();
        assertThat(result, is(cola));
    }

}
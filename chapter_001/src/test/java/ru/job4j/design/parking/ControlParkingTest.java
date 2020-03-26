package ru.job4j.design.parking;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class ControlParkingTest {
    @Test
    public void whenCarParkedToCarParking() {
        Parkable expected = Vehicle.createCar();
        Parking parking = new VehicleParking(10, 10);
        ControlParking controller = new ControlParking(parking);
        controller.park(expected);
        Parkable actual = parking.parkableList(VehicleType.CAR)[0];
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTruckParkedToTruckParking() {
        Parkable expected = Vehicle.createTruck(3);
        Parking parking = new VehicleParking(10, 10);
        ControlParking controller = new ControlParking(parking);
        controller.park(expected);
        Parkable actual = parking.parkableList(VehicleType.TRUCK)[0];
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTruckParkedToCarParking() {
        Parkable expected = Vehicle.createTruck(3);
        Parking parking = new VehicleParking(10, 0);
        ControlParking controller = new ControlParking(parking);
        controller.park(expected);
        Parkable actual = parking.parkableList(VehicleType.CAR)[0];
        assertThat(actual, is(expected));
    }

    @Test
    public void when3TruckParks2Parked() {
        Parkable truck1 = Vehicle.createTruck(3);
        Parkable truck2 = Vehicle.createTruck(5);
        Parkable truck3 = Vehicle.createTruck(4);
        Parkable[] expected = new Parkable[]{truck1, truck1, truck1,
                                             truck2, truck2, truck2,
                                             truck2, truck2, null, null};
        Parking parking = new VehicleParking(10, 0);
        ControlParking controller = new ControlParking(parking);
        var res1 = controller.park(truck1);
        var res2 = controller.park(truck2);
        var res3 = controller.park(truck3);
        Parkable[] actual = parking.parkableList(VehicleType.CAR);
        assertThat(actual, is(expected));
        assertThat(res1, is(true));
        assertThat(res2, is(true));
        assertThat(res3, is(false));
    }


}

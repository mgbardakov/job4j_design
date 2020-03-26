package ru.job4j.design.parking;

public class ControlParking {
    Parking parking;

    public ControlParking(Parking parking) {
        this.parking = parking;
    }

    public boolean park(Parkable parkable) {
        return parking.add(parkable);
    }

    public void remove(Parkable parkable) {
        parking.remove(parkable);
    }
}

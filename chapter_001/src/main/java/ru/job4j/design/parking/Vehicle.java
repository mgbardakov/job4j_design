package ru.job4j.design.parking;

public class Vehicle implements Parkable {
    private VehicleType type;
    private int size;

    private Vehicle(VehicleType type, int size) {
        this.type = type;
        this.size = size;
    }

    @Override
    public VehicleType getType() {
        return type;
    }

    @Override
    public int getSize() {
        return size;
    }
    public static Vehicle createCar() {
        return new Vehicle(VehicleType.CAR, 1);
    }
    public static Vehicle createTruck(int size) {
        return new Vehicle(VehicleType.TRUCK, size);
    }
}

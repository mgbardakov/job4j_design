package ru.job4j.design.parking;

public interface Parkable {
    /**
     * gets type of vehicle
     * @return vehicle type
     */
    VehicleType getType();

    /**
     * gets size of vehicle
     * @return vehicle size
     */
    int getSize();
}

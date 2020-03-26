package ru.job4j.design.parking;

import java.util.HashMap;
import java.util.Map;

public class VehicleParking implements Parking {
    int carCapacity;
    int truckCapacity;
    Map<VehicleType, Parkable[]> divisions;

    public VehicleParking(int carCapacity, int truckCapacity) {
        this.carCapacity = carCapacity;
        this.truckCapacity = truckCapacity;
        divisions = new HashMap<>();
        divisions.put(VehicleType.CAR, new Parkable[carCapacity]);
        divisions.put(VehicleType.TRUCK, new Parkable[truckCapacity]);
    }
    @Override
    public boolean add(Parkable parkable) {
        var result = false;
        if (parkable.getType() == VehicleType.CAR || truckCapacity == 0) {
            result = addToCarParking(parkable);
        } else {
            result = addToTruckParking(parkable);
        }
        return result;
    }
    private boolean addToCarParking(Parkable parkable) {
        var result = false;
        var count = 0;
        var cars = divisions.get(VehicleType.CAR);
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                count++;
                if (count == parkable.getSize()) {
                    for (int j = 0; j < parkable.getSize(); j++) {
                        cars[i - j] = parkable;
                    }
                    result = true;
                    carCapacity -= parkable.getSize();
                    break;
                }
            } else {
                count = 0;
            }
        }
        return result;
    }
    private boolean addToTruckParking(Parkable parkable) {
        var result = false;
        var trucks = divisions.get(VehicleType.TRUCK);
        for (int i = 0; i < trucks.length; i++) {
            if (trucks[i] == null) {
                trucks[i] = parkable;
                result = true;
                truckCapacity--;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Parkable parkable) {
        var result = false;
        switch (parkable.getType()) {
            case CAR:
                result = removeFromCarParking(parkable);
                break;
            case TRUCK:
                result = removeFromTruckParking(parkable);
                if (!result) {
                    result = removeFromCarParking(parkable);
                }
                break;
            default:
                break;
        }
        return result;
    }
    private boolean removeFromCarParking(Parkable parkable) {
        var cars = divisions.get(VehicleType.CAR);
        var result = false;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == parkable) {
                cars[i] = null;
                result = true;
                carCapacity++;
            }
        }
        return result;
    }
    private boolean removeFromTruckParking(Parkable parkable) {
        var result = false;
        var trucks = divisions.get(VehicleType.TRUCK);
        for (int i = 0; i < trucks.length; i++) {
            if (trucks[i] == parkable) {
                trucks[i] = null;
                result = true;
                truckCapacity++;
                break;
            }
        }
        return result;
    }
    @Override
    public Parkable[] parkableList(VehicleType type) {
        return divisions.get(type);
    }
}

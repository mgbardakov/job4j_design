package ru.job4j.design.parking;

import java.util.List;

/**
 * parking interface
 * @author mbardakov
 * @since 26.03.2020
 */
public interface Parking {
    /**
     * adds parkable object to parking
     * @param parkable - parkable object
     */
    boolean add(Parkable parkable);

    /**
     * removes parkable object from parking
     * @param parkable - parkable object
     */
    boolean remove(Parkable parkable);

    /**
     * @return list of parkable objects in this parking
     */
    Parkable[] parkableList(VehicleType type);
}

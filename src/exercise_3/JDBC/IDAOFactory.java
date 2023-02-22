package exercise_3.JDBC;

import exercise_3.entities.TurnedOnDevices;

public interface IDAOFactory {
    DeviceDAO getDeviceDAO();

    TurnedOnDevices getTurnedOnDevices();
}

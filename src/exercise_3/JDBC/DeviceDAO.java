package exercise_3.JDBC;

import exercise_3.entities.Device;

import java.util.List;
import java.util.Scanner;

public interface DeviceDAO {

    void switchOn(Device device);

    List<Device> getAllSorted();

    List<Device> getByManufacturer(String manufacturer);

    void getAllPower();

    void switchOff(long id);

}

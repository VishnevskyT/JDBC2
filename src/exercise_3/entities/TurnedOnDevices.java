package exercise_3.entities;

import java.util.ArrayList;
import java.util.List;

public class TurnedOnDevices {

    private List<Device> turnedOnDevices;

    private double powerConsumption;

    public TurnedOnDevices() {
        turnedOnDevices = new ArrayList<Device>();
    }

    public void turnOnDevice(Device device) {
        turnedOnDevices.add(device);
        powerConsumption += device.getPower();
    }

    public double powerConsumption() {
        return powerConsumption;
    }

    public List<Device> findDeviceByManufacturer(String manufacturer) {
        List<Device> devices = new ArrayList<>();

        for (Device device : turnedOnDevices) {
            if (device.getManufacturer().equalsIgnoreCase(manufacturer)) {
                devices.add(device);
            }
        }
        return devices;
    }

    public List<Device> getTurnedOnDevices(String manufacturer) {
        List<Device> result = new ArrayList<>();

        for (Device device : turnedOnDevices)
            if (manufacturer.equalsIgnoreCase(device.getManufacturer())) {
                result.add(device);
            }
        return result;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    @Override
    public String toString() {
        return turnedOnDevices.toString();
    }

    public List<Device> getTurnedOnDevices() {
        return turnedOnDevices;
    }
}

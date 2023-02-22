package exercise_3.entities;

public class Device {

    private long id;
    private String name;
    private double power;
    private String manufacturer;

    public Device(long id, String name, double power, String manufacturer) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.manufacturer = manufacturer;
    }

    public Device() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", power=" + power +
                ", manufacturer='" + manufacturer + '\'' +
                "}\n";
    }
}

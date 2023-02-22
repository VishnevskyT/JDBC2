package exercise_3.JDBC;

import exercise_3.entities.Device;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DeviceJDBCDAO implements DeviceDAO {

    @Override
    public void switchOn(Device device) {
        Connection connection = null;

        connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("INSERT INTO turnedondevices(name, power, manufacturer) VALUES (?, ?, ?)");
            statement.setString(1, device.getName());
            statement.setDouble(2, device.getPower());
            statement.setString(3, device.getManufacturer());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    @Override
    public List<Device> getAllSorted() {

        List<Device> devices = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM turnedondevices ORDER BY power, name");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(2);
                double power = resultSet.getDouble(3);
                String manufacturer = resultSet.getString(4);
                Device device = new Device();
                device.setName(name);
                device.setPower(power);
                device.setManufacturer(manufacturer);
                devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

        return devices;
    }

    @Override
    public List<Device> getByManufacturer(String manufacturer) {
        List<Device> devices = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM turnedondevices WHERE manufacturer LIKE CONCAT(?,'%')");

            statement.setString(1, manufacturer);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                double power = resultSet.getDouble(3);
                String selectedManufacturer = resultSet.getString(4);
                Device device = new Device();
                device.setId(id);
                device.setName(name);
                device.setPower(power);
                device.setManufacturer(selectedManufacturer);
                devices.add(device);

            }
                return devices;


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

        return null;
    }

    @Override
    public void getAllPower() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT SUM(power) FROM turnedondevices");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            double totalPower = resultSet.getDouble(1);
            System.out.println("Поточна потужність споживання: " + totalPower + " Вт;");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    @Override
    public void switchOff(long id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("DELETE FROM turnedondevices WHERE id = ?");

            statement.setLong(1, id);

            int res = statement.executeUpdate();
            if (res > 0) {
                System.out.println("Прилад з id " + id + " вимкнено");
            } else {
                System.out.println("Такого приладу не знайдено. Спробуйте інший id.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/homedevices", "root", "root");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

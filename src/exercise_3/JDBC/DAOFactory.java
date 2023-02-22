package exercise_3.JDBC;

import exercise_3.entities.TurnedOnDevices;

public class DAOFactory implements IDAOFactory {

    private static IDAOFactory factory;

    private DAOFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized IDAOFactory getInstance() {
        if (factory == null) {
            factory = new DAOFactory();
        }
        return factory;
    }


    @Override
    public DeviceDAO getDeviceDAO() {
        return new DeviceJDBCDAO();
    }

    @Override
    public TurnedOnDevices getTurnedOnDevices() {
        return null;
    }
}

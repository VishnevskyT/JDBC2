package exercise_3;

import exercise_3.JDBC.DAOFactory;
import exercise_3.JDBC.DeviceDAO;
import exercise_3.JDBC.IDAOFactory;
import exercise_3.entities.Device;

import java.util.List;
import java.util.Scanner;

public class DAOController {


    public static void main(String[] args) {
        IDAOFactory factory = DAOFactory.getInstance();
        DeviceDAO deviceDAO = factory.getDeviceDAO();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Вітаємо у Контролері DAO для керування електроприладами.\n" +
                "1 - підключити новий прилад\n" +
                "2 - поточна потужність споживання\n" +
                "3 - сортування приладів за потужністю споживання\n" +
                "4 - знайти прилад за виробником\n" +
                "5 - вимкнути прилад\n" +
                "6 - вихід з Контролера");

        DAOController controller = new DAOController();
        boolean isWorking = true;

        while (isWorking) {
            System.out.print("Ви в меню Контролера DAO: _");
            int switcher = scanner.nextInt();

            switch (switcher) {
                case 1:
                    controller.switchOnDevice(scanner);
                    break;
                case 2:
                    deviceDAO.getAllPower();
                    break;
                case 3:
                    List<Device> devices = deviceDAO.getAllSorted();

                    for ( Device device: devices) {
                        System.out.print(device);
                    }
                    break;
                case 4:
                    controller.findDeviceByManufacturer(scanner);
                    break;
                case 5:
                    controller.switchOffDevice(scanner);
                    break;
                case 6:
                    isWorking = false;
            }

        }
        System.out.println("Controller is turned off");

    }


    private void switchOnDevice(Scanner scanner) {
        IDAOFactory factory = DAOFactory.getInstance();
        DeviceDAO deviceDAO = factory.getDeviceDAO();

        System.out.print("Введіть назву приладу: ");
        String name = scanner.next();
        System.out.print("Введіть потужність " + name + ": ");
        double power = scanner.nextDouble();
        System.out.print("Введіть виробника " + name + ": ");
        String manufacturer = scanner.next();

        Device device = new Device();
        device.setName(name);
        device.setPower(power);
        device.setManufacturer(manufacturer);
        System.out.println("Увімкнено новий пристрій: " + device);

        deviceDAO.switchOn(device);

    }


    private void switchOffDevice(Scanner scanner) {
        IDAOFactory factory = DAOFactory.getInstance();
        DeviceDAO deviceDAO = factory.getDeviceDAO();

        System.out.print("Введіть id приладу,який потрібно вимкнути: ");
        long id = scanner.nextInt();

        deviceDAO.switchOff(id);

    }

    private void findDeviceByManufacturer (Scanner scanner) {
        IDAOFactory factory = DAOFactory.getInstance();
        DeviceDAO deviceDAO = factory.getDeviceDAO();
        System.out.print("Пошук за виробником: ");
        String manufacturer = scanner.next();

        List<Device> devices = deviceDAO.getByManufacturer(manufacturer);

        for (Device device: devices) {
            if (device == null) {
                System.out.println("Такого виробника не знайдено =(");
            }
            System.out.print(device);

        }

    }

}
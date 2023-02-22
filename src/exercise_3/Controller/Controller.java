package exercise_3.Controller;

import exercise_3.entities.Device;
import exercise_3.entities.TurnedOnDevices;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Вариант В
 * Создать консольное приложение, удовлетворяющее следующим требованиям:
 * • Использовать возможности ООП: классы, наследование, полиморфизм, инкапсуляция.
 * • Каждый класс должен иметь отражающее смысл название и информативный состав.
 * • Наследование должно применяться только тогда, когда это имеет смысл.
 * • При кодировании должны быть использованы соглашения об оформлении
 * кода java code convention.
 * • Классы должны быть грамотно разложены по пакетам.
 * • Консольное меню должно быть минимальным.
 * • Для хранения параметров инициализации можно использовать файлы.
 * *******************************************************************************
 * Домашние электроприборы. Определить иерархию электроприборов.
 * Включить некоторые в розетку. Подсчитать потребляемую мощность.
 * Провести сортировку приборов в квартире на основе мощности. Найти
 * прибор в квартире, соответствующий заданному диапазону параметров.
 *
 * @author Vishnevsky
 * @version 1.0 beta
 * @since February 2023
 */

public class Controller {

    private TurnedOnDevices turnedOnDevices;

    public Controller() {
        turnedOnDevices = new TurnedOnDevices();
    }

    private void turnOnDevice(Scanner scanner) {
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

        turnedOnDevices.turnOnDevice(device);

    }

    private void findDeviceByManufacturer(Scanner scanner) {
        System.out.print("Пошук за виробником: ");
        String manufacturer = scanner.next();
        List<Device> listByManufacturer = turnedOnDevices.findDeviceByManufacturer(manufacturer);
        System.out.println(listByManufacturer);
    }

    public void sort() {
        List<Device> devices = turnedOnDevices.getTurnedOnDevices();
        devices.sort(Comparator.comparing(Device::getPower));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вітаємо у Контролері для керування електроприладами.\n" +
                "1 - підключити новий прилад\n" +
                "2 - поточна потужність споживання\n" +
                "3 - сортування приладів за потужністю споживання\n" +
                "4 - знайти прилад за виробником\n" +
                "5 - вихід з Контролера");

        Controller controller = new Controller();
        boolean isWorking = true;

        while (isWorking) {
            int switcher = scanner.nextInt();

            switch (switcher) {
                case 1:
                    controller.turnOnDevice(scanner);
                    System.out.println(controller.turnedOnDevices);
                    break;
                case 2:
                    System.out.println("Поточна потужність споживання: " + controller.turnedOnDevices.powerConsumption() + " Вт;");
                    break;
                case 3:
                    controller.sort();
                    System.out.println(controller.turnedOnDevices);
                    break;
                case 4:
                    controller.findDeviceByManufacturer(scanner);
                    break;
                case 5:
                    isWorking = false;
            }


        }
        System.out.println("Controller is turned off");
    }

}

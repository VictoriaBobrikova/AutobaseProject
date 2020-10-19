package main.java.ru.autobase;

import main.java.ru.autobase.entity.Car;
import main.java.ru.autobase.entity.ConnectDriverCar;
import main.java.ru.autobase.entity.Driver;
import main.java.ru.autobase.service.CarService;
import main.java.ru.autobase.service.ConnectDriverCarService;
import main.java.ru.autobase.service.DriverService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Working with Autobase. Welcome!");
        String driverName;
        String carNumber;
        String carMark;
        Integer id;

        boolean exit = false;

        while (!exit) {
            System.out.println("Please input the number of your query and press \"Enter\":\n" +
                    "1 - create new instance\n" +
                    "2 - get instance from base\n" +
                    "3 - change instance\n" +
                    "4 - delete instance\n" +
                    "To exit press #");
            if (scan.hasNext()) {
                String input = scan.nextLine();

                outer:
                switch (input) {
                    case "1":
                        System.out.println("Choose the next number\n" +
                                "1 - create a new driver note\n" +
                                "2 - create a new car note\n" +
                                "3 - create a new driver-car connection\n" +
                                "To return to main menu press #");
                        if (scan.hasNext()) {
                            input = scan.nextLine();


                            switch (input) {
                                case "1":
                                    System.out.println("Input driver's name and surname (To return to main menu press #)");
                                    if (scan.hasNext()) {
                                        driverName = scan.nextLine();
                                        if (driverName.equals("#")) break outer;
                                        Driver driverCreate = new Driver(driverName);
                                        DriverService.createService(driverCreate);
                                        System.out.println("OK");
                                    }
                                    break outer;
                                case "2":
                                    System.out.println("Input car number (To return to main menu press #)");
                                    if (scan.hasNext()) {
                                        carNumber = scan.nextLine();
                                        if (carNumber.equals("#")) break outer;
                                        System.out.println("Input car mark (To return to main menu press #)");
                                        if (scan.hasNext()) {
                                            carMark = scan.nextLine();
                                            if (carMark.equals("#")) break outer;
                                            Car car = new Car(carNumber, carMark);
                                            CarService.createService(car);
                                            System.out.println("OK");
                                        } else System.err.println("Car number or mark missed, try again");
                                    }
                                    break;
                                case "3":
                                    System.out.println("Input driver id");
                                    if (scan.hasNext()) {
                                        try {
                                            int id_d = scan.nextInt();
                                            System.out.println("Input car id");
                                            if (scan.hasNext()) {
                                                try {
                                                    int id_c = scan.nextInt();
                                                    ConnectDriverCar conDrCar = new ConnectDriverCar(id_d, id_c);
                                                    ConnectDriverCarService.createService(conDrCar);
                                                    System.out.println("OK");
                                                } catch (InputMismatchException e) {
                                                    System.err.println("id should be a number, try again");
                                                }
                                            }
                                        } catch (InputMismatchException e) {
                                            System.err.println("id should be a number, try again");
                                            break outer;
                                        } finally {
                                            scan.nextLine();
                                        }
                                    }
                                    break outer;
                                case "#":
                                    break outer;
                                default:
                                    System.err.println("Wrong query, try again");
                                    break outer;
                            }
                        }
                        break;
                    case ("2"):
                        System.out.println("Choose the next number\n" +
                                "1 - get drivers...\n" +
                                "2 - get cars...\n" +
                                "3 - get driver-car pairs...");
                        if (scan.hasNext()) {
                            input = scan.nextLine();
                            switch (input) {
                                case ("1"):
                                    System.out.println("Choose a variant number\n" +
                                            "1 - get all drivers\n" +
                                            "2 - get all alphabetically\n" +
                                            "3 - get driver by id\n" +
                                            "4 - get drivers by car mark\n" +
                                            "5 - get drivers by car number or car id\n" +
                                            "To return to main menu press #");
                                    if (scan.hasNext()) {
                                        input = scan.nextLine();
                                        switch (input) {
                                            case ("1"):
                                                List<Driver> driverList1 = DriverService.getAllService();
                                                for (Driver d : driverList1) {
                                                    System.out.println(d.getIdDriver() + " " + d.getDriverName());
                                                }
                                                break;
                                            case ("2"):
                                                List<Driver> driverList2 = DriverService.getAllAlphabetOrderService();
                                                for (Driver d : driverList2) {
                                                    System.out.println(d.getIdDriver() + " " + d.getDriverName());
                                                }
                                                break;
                                            case ("3"):
                                                System.out.println("Input driver's id");
                                                if (scan.hasNext()) {
                                                    try {
                                                        id = scan.nextInt();
                                                        Driver driverId = DriverService.getByIdService(id);
                                                        System.out.println(driverId.getDriverName());
                                                    } catch (InputMismatchException e) {
                                                        System.err.println("id should be a number, try again");
                                                        break outer;
                                                    } finally {
                                                        scan.nextLine();
                                                    }
                                                }
                                                break outer;
                                            case ("4"):
                                                System.out.println("Input car mark");
                                                if (scan.hasNext()) {
                                                    carMark = scan.nextLine();
                                                    List<Driver> driverList4 = DriverService.getByCarMarkService(carMark);
                                                    if (driverList4.isEmpty()) {
                                                        System.err.println("No such car mark");
                                                        break outer;
                                                    }
                                                    for (Driver d : driverList4) {
                                                        System.out.println(d.getIdDriver() + " " + d.getDriverName());
                                                    }
                                                }
                                                break outer;
                                            case ("5"):
                                                System.out.println("Input car number or car id");
                                                if (scan.hasNext()) {
                                                    carNumber = scan.nextLine();
                                                    try {
                                                        id = Integer.parseInt(carNumber);
                                                        List<Driver> driverList5 = DriverService.getByCarIdService(id);
                                                        if (driverList5.isEmpty()) {
                                                            System.err.println("No such car id");
                                                            break outer;
                                                        }
                                                        for (Driver d : driverList5) {
                                                            System.out.println(d.getIdDriver() + " " + d.getDriverName());
                                                        }
                                                    } catch (NumberFormatException e) {
                                                        List<Driver> driverList5 = DriverService.getByCarNumberService(carNumber);
                                                        if (driverList5.isEmpty()) {
                                                            System.err.println("No such car number");
                                                            break outer;
                                                        }
                                                        for (Driver d : driverList5) {
                                                            System.out.println(d.getIdDriver() + " " + d.getDriverName());
                                                        }
                                                    }
                                                }
                                                break outer;
                                            case "#":
                                                break outer;
                                            default:
                                                System.err.println("Wrong query, try again");
                                                break outer;
                                        }
                                    }
                                    break;
                                case ("2"):
                                    System.out.println("Choose a variant number\n" +
                                            "1 - get all cars\n" +
                                            "2 - get by id\n" +
                                            "3 - get by car mark\n" +
                                            "4 - get by driver name or id\n" +
                                            "To return to main menu press #");
                                    if (scan.hasNext()) {
                                        input = scan.nextLine();
                                        switch (input) {
                                            case ("1"):
                                                List<Car> carList1 = CarService.getAllService();
                                                for (Car c : carList1) {
                                                    System.out.println(c.getIdCar() + " " + c.getCarNumber() + " " + c.getCarMark());
                                                }
                                                break outer;
                                            case ("2"):
                                                System.out.println("Input car's id");
                                                if (scan.hasNext()) {
                                                    try {
                                                        id = scan.nextInt();
                                                        Car carId = CarService.getByIdService(id);
                                                        System.out.println(carId.getCarNumber() + " " + carId.getCarMark());
                                                    } catch (InputMismatchException e) {
                                                        System.err.println("id should be a number, try again");
                                                        break outer;
                                                    } finally {
                                                        scan.nextLine();
                                                    }
                                                }
                                                break outer;
                                            case ("3"):
                                                System.out.println("Input car mark");
                                                if (scan.hasNext()) {
                                                    carMark = scan.nextLine();
                                                    List<Car> carList2 = CarService.getByCarMarkService(carMark);
                                                    if (carList2.isEmpty()) {
                                                        System.err.println("No such mark");
                                                        break outer;
                                                    }
                                                    for (Car c : carList2) {
                                                        System.out.println(c.getIdCar() + " " + c.getCarNumber());
                                                    }
                                                }
                                                break outer;
                                            case ("4"):
                                                System.out.println("Input driver name and surname or driver id (a number)");
                                                if (scan.hasNext()) {
                                                    driverName = scan.nextLine();
                                                    try {
                                                        id = Integer.parseInt(driverName);
                                                        List<Car> carList3 = CarService.getByDriverIdService(id);
                                                        if (carList3.isEmpty()) {
                                                            System.err.println("No such driver id");
                                                            break outer;
                                                        }
                                                        for (Car c : carList3) {
                                                            System.out.println(c.getCarNumber());
                                                        }
                                                    } catch (NumberFormatException e) {
                                                        List<Car> carList3 = CarService.getByDriverNameService(driverName);
                                                        if (carList3.isEmpty()) {
                                                            System.err.println("No such driver name");
                                                            break outer;
                                                        }
                                                        for (Car c : carList3) {
                                                            System.out.println(c.getIdCar() + " " + c.getCarNumber() + " " + c.getCarMark());
                                                        }
                                                    }
                                                }
                                                break outer;
                                            case "#":
                                                break outer;
                                            default:
                                                System.err.println("Wrong query, try again");
                                                break outer;
                                        }
                                    }
                                    break;
                                case ("3"):
                                    System.out.println("Choose a variant number\n" +
                                            "1 - get all pairs with id\n" +
                                            "2 - get all pairs with names\n" +
                                            "To return to main menu press #");
                                    if (scan.hasNext()) {
                                        input = scan.nextLine();
                                        switch (input) {
                                            case ("1"):
                                                List<ConnectDriverCar> conDrCarList1 = ConnectDriverCarService.getAllService();
                                                System.out.println("Driver id" + "  " + "Car id");
                                                for (ConnectDriverCar con : conDrCarList1) {
                                                    System.out.println("   " + con.getIdDriverCon() + "         " + con.getIdCarCon());
                                                }
                                                break outer;
                                            case ("2"):
                                                Map<String, String> conDrCarMap = ConnectDriverCarService.getAllWithNamesService();
                                                for (Map.Entry<String, String> entry : conDrCarMap.entrySet()) {
                                                    System.out.println(entry.getKey() + "       " + entry.getValue());
                                                }
                                                break outer;
                                            case "#":
                                                break outer;
                                            default:
                                                System.err.println("Wrong query, try again");
                                                break outer;
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("Wrong query, try again");
                                    break;
                            }
                        }
                        break;
                    case ("3"):
                        System.out.println("Choose a variant number\n" +
                                "1 - change driver note\n" +
                                "2 - change car note\n" +
                                "To return to main menu press #");
                        if (scan.hasNext()) {
                            input = scan.nextLine();
                            switch (input) {
                                case ("1"):
                                    System.out.println("Input driver id to change");
                                    if (scan.hasNext()) {
                                        String ID = scan.nextLine();
                                        try {
                                            id = Integer.parseInt(ID);
                                            System.out.println("Input new driver name");
                                            if (scan.hasNext()) {
                                                driverName = scan.nextLine();
                                                Driver driverUpd = new Driver(id, driverName);
                                                DriverService.updateService(driverUpd);
                                                System.out.println("OK");
                                            }
                                        } catch (NumberFormatException e) {
                                            System.err.println("id should be a number");
                                        }
                                    }
                                    break outer;
                                case ("2"):
                                    System.out.println("Input car id to change");
                                    if (scan.hasNext()) {
                                        String ID = scan.nextLine();
                                        try {
                                            id = Integer.parseInt(ID);
                                            System.out.println("Input new car number");
                                            if (scan.hasNext()) {
                                                carNumber = scan.nextLine();
                                                Car carUpd = new Car(id, carNumber);
                                                CarService.updateService(carUpd);
                                                System.out.println("OK");
                                            }
                                        } catch (NumberFormatException e) {
                                            System.err.println("id should be a number");
                                        }
                                    }
                                    break outer;
                                case ("#"):
                                    break outer;
                            }
                        }
                        break;
                    case ("4"):
                        System.out.println("Choose a variant number\n" +
                                "1 - delete driver note\n" +
                                "2 - delete car note\n" +
                                "To return to main menu press #");
                        if (scan.hasNext()) {
                            input = scan.nextLine();
                            switch (input) {
                                case ("1"):
                                    System.out.println("Input driver id to delete");
                                    if (scan.hasNext()) {
                                        try {
                                            id = scan.nextInt();
                                            DriverService.deleteService(id);
                                            System.out.println("OK");
                                        } catch (InputMismatchException e) {
                                            System.err.println("id should be a number");
                                        } finally {
                                            scan.nextLine();
                                        }
                                    }
                                    break outer;
                                case ("2"):
                                    System.out.println("Input car id to delete");
                                    if (scan.hasNext()) {
                                        try {
                                            id = scan.nextInt();
                                            CarService.deleteService(id);
                                            System.out.println("OK");
                                        } catch (InputMismatchException e) {
                                            System.err.println("id should be a number");
                                        } finally {
                                            scan.nextLine();
                                        }
                                    }
                                    break outer;
                                case ("#"):
                                    break outer;
                            }
                        }
                        break;
                    case ("#"):
                        exit = true;
                        break;
                    default:
                        System.err.println("Wrong query, try again");
                        break;
                }
                System.out.println("Thanks :)");
            }
        }
        scan.close();
    }
}

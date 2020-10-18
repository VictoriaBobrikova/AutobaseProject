package main.java.ru.autobase;

import main.java.ru.autobase.entity.Car;
import main.java.ru.autobase.entity.ConnectDriverCar;
import main.java.ru.autobase.service.CarService;
import main.java.ru.autobase.service.ConnectDriverCarService;
import main.java.ru.autobase.service.DriverService;

import java.sql.*;
import java.util.*;

import main.java.ru.autobase.entity.Driver;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Working with Autobase. Welcome!");
        String inputString;
        String driverName;
        String carNumber;
        String carMark;
        int id;
        String pattern;

        boolean exit = false;

        while(!exit) {
            System.out.println("Please input the number of your query and press \"Enter\":\n" +
                    "1 - create new instance\n" +
                    "2 - get instance from base\n" +
                    "3 - change instance\n" +
                    "4 - delete instance\n" +
                    "To exit press #");
            if (scan.hasNext()) {
                String input = scan.nextLine();
                switch (input) {
                    case ("1"):
                        System.out.println("Choose the next number\n" +
                                           "1 - create a new driver note\n" +
                                           "2 - create a new car note");
                        if (scan.hasNext()) {
                            inputString = scan.nextLine();
                            if (inputString.equals("1")) {
                                System.out.println("Input driver's name and surname (To exit press #)");
                                pattern = "";
                                if (scan.hasNext()) {
                                    driverName = scan.nextLine();
                                    if(driverName.equals("#")) break;
                                    Driver driverCreate = new Driver(driverName);
                                    DriverService.createService(driverCreate);
                                    System.out.println("OK");
                                }
                            } else if (inputString.equals("2")) {
                                System.out.println("Input car number and car mark with space delimiter (To exit press #)");
                                pattern = "";
                                if (scan.hasNext()) {
                                    carNumber = scan.next();
                                    if(carNumber.equals("#")) break;
                                    carMark = scan.next();
                                    Car car = new Car(carNumber, carMark);
                                    CarService.createService(car);
                                    System.out.println("OK");
                                }
                            } else System.out.println("Wrong query, try again");
                        }
                        break;
                    case ("2"):
                        System.out.println("Choose the next number\n" +
                                           "1 - get drivers...\n" +
                                           "2 - get cars...\n" +
                                           "3 - get driver-car pairs...");
                        if (scan.hasNext()) {
                            inputString = scan.nextLine();
                            switch (inputString) {
                                case ("1"):
                                    System.out.println("Choose a variant number\n" +
                                            "1 - get all drivers\n" +
                                            "2 - get all alphabetically\n" +
                                            "3 - get driver by id\n" +
                                            "4 - get drivers by car mark\n" +
                                            "5 - get drivers by car number");
                                    if (scan.hasNext()) {
                                        inputString = scan.nextLine();
                                        switch (inputString) {
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
                                                //что-то не так
                                                if (scan.hasNext()) {
                                                    try {
                                                        id = scan.nextInt();
                                                    } catch (InputMismatchException e) {
                                                        System.err.println("id should be a number, try again");
                                                        break;
                                                    }
                                                    Driver driverId = DriverService.getByIdService(id);
                                                    System.out.println(driverId.getDriverName());
                                                }
                                                break;
                                            case ("4"):
                                                System.out.println("Input car mark");
                                                if (scan.hasNext()) {
                                                    carMark = scan.nextLine();
                                                    List<Driver> driverList4 = DriverService.getByCarMarkService(carMark);
                                                    if (driverList4.isEmpty()) {
                                                        System.out.println("No such mark");
                                                        break;
                                                    }
                                                    for (Driver d : driverList4) {
                                                        System.out.println(d.getIdDriver() + " " + d.getDriverName());
                                                    }
                                                }
                                                break;
                                            case ("5"):
                                                System.out.println("Input car number");
                                                if (scan.hasNext()) {
                                                    carNumber = scan.nextLine();
                                                    List<Driver> driverList5 = DriverService.getByCarNumberService(carNumber);
                                                    if (driverList5.isEmpty()) {
                                                        System.out.println("No such car number");
                                                        break;
                                                    }
                                                    for (Driver d : driverList5) {
                                                        System.out.println(d.getIdDriver() + " " + d.getDriverName());
                                                    }
                                                }
                                                break;
                                            default:
                                                System.out.println("Wrong query, try again");
                                                break;
                                        }
                                    }
                                    break;
                                case ("2"):
                                    System.out.println("Input car number and car mark with space delimiter");
                                    if (scan.hasNext()) {
                                        carNumber = scan.next();
                                        carMark = scan.next();
                                        Car car = new Car(carNumber, carMark);
                                        CarService.createService(car);
                                        System.out.println("OK");
                                    }
                                    break;
                                case("3"):
                                    System.out.println("Input car number and car mark with space delimiter");
                                    if (scan.hasNext()) {
                                        carNumber = scan.next();
                                        carMark = scan.next();
                                        Car car = new Car(carNumber, carMark);
                                        CarService.createService(car);
                                        System.out.println("OK");
                                    }
                                default:
                                    System.out.println("Wrong query, try again");
                                    break;
                            }
                        }
                        break;
                    case ("3"):
                        System.out.println("hi3");
                        break;
                    case ("4"):
                        System.out.println("hi4");
                        break;
                    case ("#"):
                        exit = true;
                        break;
                    default:
                        System.out.println("Wrong query, try again");
                        break;
                }
                System.out.println("Thanks :)");
            }
        }
        scan.close();




//        Driver driver = DriverService.getByIdService(1);
//        System.out.println(driver.getDriverName());

//        Driver driver = new Driver(1,"Tolya");
//        DriverService.updateService(driver);

//        DriverService.deleteService(30);



//        System.out.println("driver1");
//        List<Driver> driverList = DriverService.getByCarMarkService("BMW");
//        for (Driver d : driverList) {
//            System.out.println(d.getIdDriver() + " " + d.getDriverName());
//        }
//
//        System.out.println("driver2");
//        List<Driver> driverList = DriverService.getByCarNumberService("а123хх");
//        for (Driver d : driverList) {
//            System.out.println(d.getIdDriver() + " " + d.getDriverName());
//        }


//        List<Car> carList = CarService.getByCarMarkService("Audi");
//        for (Car c : carList) {
//            System.out.println(c.getIdCar() + " " + c.getCarNumber());
//        }
//        System.out.println("name");
//        List<Car> carList1 = CarService.getByDriverNameService("Arina Panova");
//        for (Car c : carList1) {
//            System.out.println(c.getIdCar() + " " + c.getCarNumber() + " " + c.getCarMark());
//        }

//        System.out.println("car3");
//        Car car1 = CarService.getByIdService(1);
//        System.out.println(car1.getCarNumber() + " " + car1.getCarMark());

//        Car car = new Car("ц333цц", "Volvo");
//        CarService.deleteService(19);

//        System.out.println("list");
//        List<Car> carList = CarService.getAllService();
//        for (Car c : carList) {
//            if (c.getIdCar()<10) {
//                System.out.println(" " + c.getIdCar() + " " + c.getCarNumber() + " " + c.getCarMark());
//            } else {System.out.println(c.getIdCar() + " " + c.getCarNumber() + " " + c.getCarMark());}
//        }

//        System.out.println("get");
//        Car car_get = CarService.getByIdService(1);
//        System.out.println(car_get.getIdCar() + " " + car_get.getCarNumber() + " " + car_get.getCarMark());
//
//        System.out.println("upd");
//        Car car2 = new Car(1,"а123хх");
//        CarService.updateService(car2);
//

//        Car car_get1 = CarService.getByIdService(17);
//        System.out.println(car_get1.getIdCar() + " " + car_get1.getCarNumber() + " " + car_get1.getCarMark());

//        ConnectDriverCar conDrCar = new ConnectDriverCar(16,16);
//        ConnectDriverCarService.deleteService(conDrCar);

//        System.out.println("map");
//        List<String> conDrCarList = ConnectDriverCarService.getAllWithNamesService();
//        List<String> carNumberList = new ArrayList<>();
//        Set<String> driverNameSet = new HashSet<>();
//        for (String con : conDrCarList) {
//            Scanner scan = new Scanner(con);
//            String driverName = scan.next() + " " + scan.next();
//            carNumberList.add(scan.next());
//            if (!driverNameSet.add(driverName)) carNumberList.clear();
//            System.out.println();
//        }


//        Map<String, String> conDrCarMap = ConnectDriverCarService.getAllWithNamesService();
//        for (Map.Entry<String, String> entry : conDrCarMap.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }

//        ConnectDriverCar conDrCar = new ConnectDriverCar(16,16);
//        ConnectDriverCarService.createService(conDrCar);
//
//        List<ConnectDriverCar> conDrCarList = ConnectDriverCarService.getAllService();
//        for (ConnectDriverCar c : conDrCarList) {
//            System.out.println(c.getIdDriverCon() + " " + c.getIdCarCon());
//        };

//        List<Car> cars = ConnectDriverCarService.getCarsByDriverIdService(1);
//        for (Car c : cars) {
//            System.out.println(c.getCarNumber());
//        };

//        List<Driver> drivers = ConnectDriverCarService.getDriversByCarIdService(1);
//        for (Driver d : drivers) {
//            System.out.println(d.getDriverName());
//        }


    }
}

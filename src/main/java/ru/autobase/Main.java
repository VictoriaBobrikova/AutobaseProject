package main.java.ru.autobase;

import main.java.ru.autobase.entity.Car;
import main.java.ru.autobase.entity.ConnectDriverCar;
import main.java.ru.autobase.service.CarService;
import main.java.ru.autobase.service.ConnectDriverCarService;
import main.java.ru.autobase.service.DriverService;

import java.sql.*;
import java.util.*;

import main.java.ru.autobase.entity.Driver;

/*
Консоль:
Введите подходящий номер запроса:
1 - создать новую запись в базе
2 - найти запись в базе
3 - изменить записаь в базе
4 - удалить запись из базы
Выберите требуемый запрос:
1 - записать в базу нового водителя
2 - записать в базу новый автомобиль
3? записать в базу новую марку автомобиля

1 - вывести всех водителей
2 - найти водителей по параметрам
3 - найти автомобиль по параметрам

1 - изменить запись водителя
2 - изменить запись автомобиля

1 - удалить запись водителя
2 - удалить запись автомобиля
 */
public class Main {
    public static void main(String[] args) {

//        Driver driver = new Driver("Katya");
//        DriverService.createService(driver);

//        Driver driver = DriverService.getByIdService(1);
//        System.out.println(driver.getDriverName());

//        Driver driver = new Driver(1, "Tolya");
//        DriverService.updateService(driver);

//        DriverService.deleteService(30);

//        List<Driver> driverList = DriverService.getAllAlphabetOrderService();
//        for (Driver d : driverList) {
//            if (d.getIdDriver()<10) {
//                System.out.println(" " + d.getIdDriver() + " " + d.getDriverName());
//            } else {System.out.println(d.getIdDriver() + " " + d.getDriverName());}
//        }

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

//        System.out.println("car");
//        Car car = CarService.getByMarkService("Audi");
//        for (Driver d : driverList) {
//            if (d.getIdDriver()<10) {
//                System.out.println(" " + d.getIdDriver() + " " + d.getDriverName());
//            } else {System.out.println(d.getIdDriver() + " " + d.getDriverName());}
//        }
//        System.out.println(car.getIdCar() + " " + car.getCarNumber() + " " + car.getCarMark());

//        System.out.println("car1");
//        Car car1 = CarService.getByDriverNameService("Vadim Privalov");
//        System.out.println(car1.getIdCar() + " " + car1.getCarNumber() + " " + car1.getCarMark());

//        System.out.println("car3");
//        Car car1 = CarService.getByIdService(1);
//        System.out.println(car1.getCarNumber() + " " + car1.getCarMark());

//        Car car = new Car("ц333цц", "Volvo");
//        CarService.createService(car);
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


        Map<String, String> conDrCarMap = ConnectDriverCarService.getAllWithNamesService();
        for (Map.Entry<String, String> entry : conDrCarMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

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

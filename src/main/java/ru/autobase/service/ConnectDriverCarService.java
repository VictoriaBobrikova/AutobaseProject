package main.java.ru.autobase.service;

import main.java.ru.autobase.dao.CarDAO;
import main.java.ru.autobase.dao.CarDAOImpl;
import main.java.ru.autobase.dao.ConnectDriverCarDAO;
import main.java.ru.autobase.dao.ConnectDriverCarDAOImpl;
import main.java.ru.autobase.entity.Car;
import main.java.ru.autobase.entity.ConnectDriverCar;
import main.java.ru.autobase.entity.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectDriverCarService {


    public static void createService(ConnectDriverCar conDrCar) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
            conDrCarDAO.create(conDrCar);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
//
//    public static ConnectDriverCar getByIdService(Integer id) {
//        Car car = new Car();
//        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
//            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
//            car = conDrCarDAO.getById(id);
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        return conDrCar;
//    }

    public static List<ConnectDriverCar> getAllService () {
        List<ConnectDriverCar> conDrCarList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            ConnectDriverCarDAO carDAO = new ConnectDriverCarDAOImpl(connection);
            conDrCarList = carDAO.getAll();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return conDrCarList;
    }

    public static Map<String, StringBuilder> getAllWithNamesService () {
        Map<String, StringBuilder> conDrCarMap = new HashMap<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
            conDrCarMap = conDrCarDAO.getAllWithNames();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return conDrCarMap;
    }


//    public static void updateService(ConnectDriverCar conDrCar) {
//        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
//            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
//            conDrCarDAO.update(conDrCar);
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//    }


    public static void deleteService(ConnectDriverCar conDrCar) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
            conDrCarDAO.delete(conDrCar);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public static List<Car> getCarsByDriverIdService(Integer idDriver) {
        List<Car> cars = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
            cars = conDrCarDAO.getCarsByDriverId(idDriver);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return cars;
    }

    public static List<Driver> getDriversByCarIdService(Integer idCar) {
        List<Driver> drivers = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
            drivers = conDrCarDAO.getDriversByCarId(idCar);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return drivers;
    }
//
//
//    public static Car getByDriverNameService(String driverName) {
//        Car car = new Car();
//        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
//            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
//            car = conDrCarDAO.getByDriverName(driverName);
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        return car;
//    }

}

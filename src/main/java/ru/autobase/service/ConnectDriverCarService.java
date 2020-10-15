package main.java.ru.autobase.service;

import main.java.ru.autobase.dao.CarDAO;
import main.java.ru.autobase.dao.CarDAOImpl;
import main.java.ru.autobase.dao.ConnectDriverCarDAO;
import main.java.ru.autobase.dao.ConnectDriverCarDAOImpl;
import main.java.ru.autobase.entity.Car;
import main.java.ru.autobase.entity.ConnectDriverCar;

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


    public static void updateService(ConnectDriverCar conDrCar) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
            conDrCarDAO.update(conDrCar);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


//    public static void deleteService(ConnectDriverCar conDrCar) {
//        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
//            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
//            conDrCarDAO.delete(conDrCar);
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//    }
//
//
//    public static Car getByCarMarkService(String carMark) {
//        Car car = new Car();
//        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
//            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
//            car = conDrCarDAO.getByCarMark(carMark);
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        return car;
//    }
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

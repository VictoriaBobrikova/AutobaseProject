package main.java.ru.autobase.service;

import main.java.ru.autobase.dao.DriverDAO;
import main.java.ru.autobase.dao.DriverDAOImpl;
import main.java.ru.autobase.entity.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverService {

    public static void createService(Driver driver) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            DriverDAO driverDAO = new DriverDAOImpl(connection);
            driverDAO.create(driver);
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
    }

    public static Driver getByIdService(Integer id) {
        Driver driver = new Driver();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            DriverDAO driverDAO = new DriverDAOImpl(connection);
            driver = driverDAO.getById(id);
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return driver;
    }

    public static List<Driver> getAllService () {
        List<Driver> driverList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            DriverDAO driverDAO = new DriverDAOImpl(connection);
            driverList = driverDAO.getAll();
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return driverList;
    }


    public static List<Driver> getAllAlphabetOrderService () {
        List<Driver> driverList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            DriverDAO driverDAO = new DriverDAOImpl(connection);
            driverList = driverDAO.getAllAlphabetOrder();
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return driverList;
    }

    public static void updateService(Driver driver) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            DriverDAO driverDAO = new DriverDAOImpl(connection);
            driverDAO.update(driver);
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
    }


    public static void deleteService(Integer id) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            DriverDAO driverDAO = new DriverDAOImpl(connection);
            driverDAO.delete(id);
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
    }


    public static List<Driver> getByCarMarkService(String carMark) {
        List<Driver> driverList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            DriverDAO driverDAO = new DriverDAOImpl(connection);
            driverList = driverDAO.getByCarMark(carMark);
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return driverList;
    }

    public static List<Driver> getByCarNumberService(String carNumber) {
        List<Driver> driverList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            DriverDAO driverDAO = new DriverDAOImpl(connection);
            driverList = driverDAO.getByCarNumber(carNumber);
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return driverList;
    }


    public static List<Driver> getByCarIdService(Integer idCar) {
        List<Driver> drivers = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            DriverDAO driverDAO = new DriverDAOImpl(connection);
            drivers = driverDAO.getByCarId(idCar);
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return drivers;
    }

}

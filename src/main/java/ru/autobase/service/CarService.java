package main.java.ru.autobase.service;

import main.java.ru.autobase.dao.CarDAO;
import main.java.ru.autobase.dao.CarDAOImpl;
import main.java.ru.autobase.dao.DriverDAO;
import main.java.ru.autobase.dao.DriverDAOImpl;
import main.java.ru.autobase.entity.Car;
import main.java.ru.autobase.entity.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarService {


    public static void createService(Car car) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            CarDAO carDAO = new CarDAOImpl(connection);
            carDAO.create(car);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Car getByIdService(Integer id) {
        Car car = new Car();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            CarDAO carDAO = new CarDAOImpl(connection);
            car = carDAO.getById(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return car;
    }


    public static List<Car> getAllService () {
        List<Car> carList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            CarDAO carDAO = new CarDAOImpl(connection);
            carList = carDAO.getAll();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return carList;
    }


    public static void updateService(Car car) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            CarDAO carDAO = new CarDAOImpl(connection);
            carDAO.update(car);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void deleteService(Integer id) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            CarDAO carDAO = new CarDAOImpl(connection);
            carDAO.delete(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public static Car getByCarMarkService(String carMark) {
        Car car = new Car();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            CarDAO carDAO = new CarDAOImpl(connection);
            car = carDAO.getByCarMark(carMark);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return car;
    }


    public static Car getByDriverNameService(String driverName) {
        Car car = new Car();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            CarDAO carDAO = new CarDAOImpl(connection);
            car = carDAO.getByDriverName(driverName);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return car;
    }

}

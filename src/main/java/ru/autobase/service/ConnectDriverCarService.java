package main.java.ru.autobase.service;

import main.java.ru.autobase.dao.ConnectDriverCarDAO;
import main.java.ru.autobase.dao.ConnectDriverCarDAOImpl;
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
            System.err.println("Query error, try again");
        }
    }


    public static List<ConnectDriverCar> getAllService () {
        List<ConnectDriverCar> conDrCarList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            ConnectDriverCarDAO carDAO = new ConnectDriverCarDAOImpl(connection);
            conDrCarList = carDAO.getAll();
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return conDrCarList;
    }


    public static Map<String, String> getAllWithNamesService () {
        Map<String, String> conDrCarMap = new HashMap<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
            conDrCarMap = conDrCarDAO.getAllWithNames();
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return conDrCarMap;
    }


    public static void deleteService(ConnectDriverCar conDrCar) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:db/Autobase")) {
            ConnectDriverCarDAO conDrCarDAO = new ConnectDriverCarDAOImpl(connection);
            conDrCarDAO.delete(conDrCar);
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
    }

}

package main.java.ru.autobase.dao;

import main.java.ru.autobase.entity.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO{

    private final Connection connection;

    public DriverDAOImpl(final Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Driver driver) {
        try (PreparedStatement prepStat = connection.prepareStatement(SQLDriver.INSERT.QUERY)) {
            prepStat.setString(1, driver.getDriverName());
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
    }


    @Override
    public Driver getById(Integer id) {
        Driver driver = new Driver();
        try (PreparedStatement prepStat = connection.prepareStatement(SQLDriver.SELECT.QUERY)) {
            prepStat.setInt(1, id);
            ResultSet rs = prepStat.executeQuery();
            if (rs.next()) {
                driver.setDriverName(rs.getString("driver_name"));
            } else {
                System.err.print("No such id, ");
            }
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return driver;
    }


    @Override
    public List<Driver> getAll() {
        List<Driver> driverList = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(SQLDriver.SELECT_ALL.QUERY);

            while (rs.next()) {
                Driver driver = new Driver();
                driver.setIdDriver(rs.getInt("id_driver"));
                driver.setDriverName(rs.getString("driver_name"));
                driverList.add(driver);
            }
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return driverList;
    }



    @Override
    public void update(Driver driver) {
        try (PreparedStatement prepStat = connection.prepareStatement(SQLDriver.UPDATE.QUERY)) {
            prepStat.setString(1, driver.getDriverName());
            prepStat.setInt(2, driver.getIdDriver());
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
    }


    @Override
    public void delete(Integer id) {
        try (PreparedStatement prepStat = connection.prepareStatement(SQLDriver.DELETE.QUERY)) {
            prepStat.setInt(1, id);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
    }


    @Override
    public List<Driver> getAllAlphabetOrder() {
        List<Driver> driverList = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(SQLDriver.SELECT_ALPHA.QUERY);

            while (rs.next()) {
                Driver driver = new Driver();
                driver.setIdDriver(rs.getInt("id_driver"));
                driver.setDriverName(rs.getString("driver_name"));
                driverList.add(driver);
            }
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return driverList;
    }

    @Override
    public List<Driver> getByCarMark(String carMark) {
        List<Driver> driverList = new ArrayList<>();

        try (PreparedStatement prepStat = connection.prepareStatement(SQLDriver.SELECT_BY_CAR_MARK.QUERY)) {
            prepStat.setString(1, carMark);
            ResultSet rs = prepStat.executeQuery();
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setIdDriver(rs.getInt("id_driver"));
                driver.setDriverName(rs.getString("driver_name"));
                driverList.add(driver);
            }
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return driverList;
    }


    @Override
    public List<Driver> getByCarNumber(String carNumber) {
        List<Driver> driverList = new ArrayList<>();
//        String sql = "SELECT id_driver, driver_name from drivers " +
//                "join dc_connection on id_driver = id_d_con " +
//                "join car_info on id_c_con = id_car " +
//                "WHERE car_number = ?";

        try (PreparedStatement prepStat = connection.prepareStatement(SQLDriver.SELECT_BY_CAR_NUMBER.QUERY)) {
            prepStat.setString(1, carNumber);
            ResultSet rs = prepStat.executeQuery();
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setIdDriver(rs.getInt("id_driver"));
                driver.setDriverName(rs.getString("driver_name"));
                driverList.add(driver);
            }
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return driverList;
    }

    @Override
    public List<Driver> getByCarId(Integer idCar) {
        List<Driver> drivers = new ArrayList<>();
        try (PreparedStatement prepStat = connection.prepareStatement(SQLDriver.SELECT_BY_CAR_ID.QUERY)) {
            prepStat.setInt(1, idCar);
            ResultSet rs  = prepStat.executeQuery();
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setIdDriver(rs.getInt("id_driver"));
                driver.setDriverName(rs.getString("driver_name"));
                drivers.add(driver);
            }
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return drivers;
    }


    enum SQLDriver {
        INSERT("INSERT INTO drivers (driver_name) VALUES (?)"),
        SELECT("SELECT driver_name FROM drivers WHERE id_driver = ?"),
        SELECT_ALL("SELECT * FROM drivers"),
        SELECT_ALPHA("SELECT * FROM drivers ORDER BY driver_name"),
        SELECT_BY_CAR_ID("SELECT id_driver, driver_name FROM car_info " +
                "JOIN dc_connection ON id_car = id_c_con " +
                "JOIN drivers ON id_d_con = id_driver " +
                "WHERE id_car = ?"),
        SELECT_BY_CAR_MARK("SELECT id_driver, driver_name from drivers " +
                "join dc_connection on id_driver = id_d_con " +
                "join car_info on id_c_con = id_car " +
                "join car_mark on id_mark_info = id_mark " +
                "WHERE UPPER(mark) LIKE UPPER(?) order by driver_name"),
        SELECT_BY_CAR_NUMBER("SELECT id_driver, driver_name from drivers " +
                "join dc_connection on id_driver = id_d_con " +
                "join car_info on id_c_con = id_car " +
                "WHERE car_number ?"),
        UPDATE("UPDATE drivers SET driver_name = ? WHERE id_driver = ?"),
        DELETE("DELETE FROM drivers WHERE id_driver = ?");

        String QUERY;

        SQLDriver(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}

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
//            int result = prepStat.executeUpdate();
//            if (result != 1) {
//                System.out.print("Invalid name");
//            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                System.err.print("Error: not found id, result is ");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return driver;
    }


    @Override
    public List<Driver> getAll() {
        List<Driver> driverList = new ArrayList<>();

        String sql = "SELECT * FROM drivers";

        try (Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Driver driver = new Driver();
                driver.setIdDriver(rs.getInt("id_driver"));
                driver.setDriverName(rs.getString("driver_name"));
                driverList.add(driver);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Integer id) {
        try (PreparedStatement prepStat = connection.prepareStatement(SQLDriver.DELETE.QUERY)) {
            prepStat.setInt(1, id);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Driver> getAllAlphabetOrder() {
        List<Driver> driverList = new ArrayList<>();

        String sql = "SELECT * FROM drivers ORDER BY driver_name";

        try (Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Driver driver = new Driver();
                driver.setIdDriver(rs.getInt("id_driver"));
                driver.setDriverName(rs.getString("driver_name"));
                driverList.add(driver);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return driverList;
    }

    @Override
    public Driver getByCarMark(String carMark) {
        Driver driver = new Driver();
        String sql = "SELECT id_driver, driver_name from drivers " +
                "join dc_connection on id_driver = id_d_con " +
                "join car_info on id_c_con = id_car " +
                "join car_mark on id_mark_info = id_mark " +
                "WHERE mark = ?";

        try (PreparedStatement prepStat = connection.prepareStatement(sql)) {
            prepStat.setString(1, carMark);
            ResultSet rs = prepStat.executeQuery();
            if (rs.next()) {
                driver.setIdDriver(rs.getInt("id_driver"));
                driver.setDriverName(rs.getString("driver_name"));
            } else {
                System.err.print("Not found this mark");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return driver;
    }


    @Override
    public Driver getByCarNumber(String carNumber) {
        Driver driver = new Driver();
        String sql = "SELECT id_driver, driver_name from drivers " +
                "join dc_connection on id_driver = id_d_con " +
                "join car_info on id_c_con = id_car " +
                "WHERE car_number = ?";

        try (PreparedStatement prepStat = connection.prepareStatement(sql)) {
            prepStat.setString(1, carNumber);
            ResultSet rs = prepStat.executeQuery();
            if (rs.next()) {
                driver.setIdDriver(rs.getInt("id_driver"));
                driver.setDriverName(rs.getString("driver_name"));
            } else {
                System.err.print("Not found this car number");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return driver;
    }

    enum SQLDriver {
        INSERT("INSERT INTO drivers (driver_name) VALUES (?)"),
        SELECT("SELECT driver_name FROM drivers WHERE id_driver = ?"),
        UPDATE("UPDATE drivers SET driver_name = ? WHERE id_driver = ?"),
        DELETE("DELETE FROM drivers WHERE id_driver = ?");

        String QUERY;

        SQLDriver(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}

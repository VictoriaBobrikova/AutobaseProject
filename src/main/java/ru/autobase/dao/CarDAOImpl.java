package main.java.ru.autobase.dao;

import main.java.ru.autobase.entity.Car;
import main.java.ru.autobase.entity.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO{

    private final Connection connection;

    public CarDAOImpl(final Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Car car) {
        String sql1 = "INSERT INTO car_mark (mark) SELECT ? " +
                "WHERE NOT EXISTS (SELECT mark FROM car_mark WHERE UPPER(mark) LIKE UPPER(?)) LIMIT 1";

        String sql2 = "INSERT INTO car_info (car_number, id_mark_info) " +
                "VALUES (?, (SELECT id_mark from car_mark WHERE UPPER(mark) LIKE UPPER(?)))";

        try (PreparedStatement prepStat = connection.prepareStatement(sql1)) {
            prepStat.setString(1, car.getCarMark());
            prepStat.setString(2, car.getCarMark());
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement prepStat = connection.prepareStatement(sql2)) {
            prepStat.setString(1, car.getCarNumber());
            prepStat.setString(2, car.getCarMark());
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Car getById(Integer id) {
        Car car = new Car();
        try (PreparedStatement prepStat = connection.prepareStatement(CarDAOImpl.SQLCar.SELECT.QUERY)) {
            prepStat.setInt(1, id);
            ResultSet rs = prepStat.executeQuery();
            car.setCarNumber(rs.getString("car_number"));
            car.setCarMark(rs.getString("mark"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return car;
    }


    @Override
    public List<Car> getAll() {
        List<Car> carList = new ArrayList<>();

        String sql = "SELECT id_car, car_number, mark FROM car_info JOIN car_mark ON id_mark_info = id_mark";

        try (Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Car car = new Car();
                car.setIdCar(rs.getInt("id_car"));
                car.setCarNumber(rs.getString("car_number"));
                car.setCarMark(rs.getString("mark"));
                carList.add(car);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return carList;
    }


    @Override
    public void update(Car car) {
        try (PreparedStatement prepStat = connection.prepareStatement(CarDAOImpl.SQLCar.UPDATE.QUERY)) {
            prepStat.setString(1, car.getCarNumber());
            prepStat.setInt(2, car.getIdCar());
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Integer id) {
        try (PreparedStatement prepStat = connection.prepareStatement(CarDAOImpl.SQLCar.DELETE.QUERY)) {
            prepStat.setInt(1, id);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Car getByCarMark(String carMark) {
        Car car = new Car();
        String sql = "SELECT id_car, car_number FROM car_info " +
                "JOIN car_mark ON id_mark_info = id_mark WHERE mark = ?";

        try (PreparedStatement prepStat = connection.prepareStatement(sql)) {
            prepStat.setString(1, carMark);
            ResultSet rs = prepStat.executeQuery();
            if (rs.next()) {
                car.setIdCar(rs.getInt("id_car"));
                car.setCarNumber(rs.getString("car_number"));
                car.setCarMark(carMark);
            } else {
                System.err.print("Not found this car mark");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return car;
    }


    @Override
    public Car getByDriverName(String driverName) {
        Car car = new Car();
        String sql = "SELECT id_car, car_number, mark FROM car_info " +
                "JOIN car_mark ON id_mark_info = id_mark " +
                "JOIN dc_connection ON id_car = id_c_con " +
                "JOIN drivers ON id_d_con = id_driver " +
                "WHERE driver_name = ?";

        try (PreparedStatement prepStat = connection.prepareStatement(sql)) {
            prepStat.setString(1, driverName);
            ResultSet rs = prepStat.executeQuery();
            if (rs.next()) {
                car.setIdCar(rs.getInt("id_car"));
                car.setCarNumber(rs.getString("car_number"));
                car.setCarMark(rs.getString("mark"));
            } else {
                System.err.print("Not found this driver name ");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return car;
    }

    enum SQLCar {
        SELECT("SELECT car_number, mark FROM car_info " +
                "JOIN car_mark ON id_mark_info = id_mark WHERE id_car = ?"),
        UPDATE("UPDATE car_info SET car_number = ? WHERE id_car = ?"),
        DELETE("DELETE FROM car_info WHERE id_car = ?");

        String QUERY;

        SQLCar(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}

package main.java.ru.autobase.dao;

import main.java.ru.autobase.entity.ConnectDriverCar;
import main.java.ru.autobase.entity.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectDriverCarDAOImpl implements ConnectDriverCarDAO{

    private final Connection connection;

    public ConnectDriverCarDAOImpl(final Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(ConnectDriverCar conDrCar) {
        try (PreparedStatement prepStat = connection.prepareStatement(DriverDAOImpl.SQLDriver.INSERT.QUERY)) {
            prepStat.setInt(1, conDrCar.getIdDriverCon());
            prepStat.setInt(1, conDrCar.getIdCarCon());
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ConnectDriverCar getById(ConnectDriverCar conDrCar) {
        //meaningless method for connection class, see another similar methods
        return null;
    }

    @Override
    public List<ConnectDriverCar> getAll() {
        List<ConnectDriverCar> conDrCarList = new ArrayList<>();

        String sql = "SELECT * FROM dc_connection ORDER BY id_d_con";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ConnectDriverCar conDrCar = new ConnectDriverCar();
                conDrCar.setIdDriverCon(rs.getInt("id_d_con"));
                conDrCar.setIdCarCon(rs.getInt("id_c_con"));
                conDrCarList.add(conDrCar);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return conDrCarList;
    }

    @Override
    public Map<String, StringBuilder> getAllWithNames() {
        Map<String, StringBuilder> conDrCarPairs = new HashMap<>();

        String sql = "SELECT driver_name, car_number FROM drivers " +
                "JOIN dc_connection ON id_driver = id_d_con " +
                "JOIN car_info ON id_c_con = id_car ORDER BY driver_name ASC";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            StringBuilder carNumber = new StringBuilder();
            rs.next();
            carNumber.append(rs.getString("car_number"));
            conDrCarPairs.put(rs.getString("driver_name"), carNumber);

            while (rs.next()) {
                if(!conDrCarPairs.containsKey(rs.getString("driver_name"))) {
                    carNumber.delete(0, carNumber.length());
                }
                carNumber.append("  ");
                carNumber.append(rs.getString("car_number"));
                conDrCarPairs.put(rs.getString("driver_name"), carNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conDrCarPairs;
    }


    @Override
    public void update(ConnectDriverCar conDrCar) {
        try (PreparedStatement prepStat = connection.prepareStatement(DriverDAOImpl.SQLDriver.UPDATE.QUERY)) {
            prepStat.setInt(1, conDrCar.getIdDriverCon());
            prepStat.setInt(2, conDrCar.getIdCarCon());
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ConnectDriverCar conDrCar) {
        try (PreparedStatement prepStat = connection.prepareStatement(DriverDAOImpl.SQLDriver.DELETE.QUERY)) {
            prepStat.setInt(1, conDrCar.getIdDriverCon());
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    enum SQLConDrCar {
        INSERT("INSERT INTO dc_connection (id_d_con, id_c_con) SELECT ?, ? " +
                "WHERE NOT EXISTS (SELECT id_d_con, id_c_con FROM dc_connection " +
                "WHERE id_d_con = ? AND id_c_con = ?) LIMIT 1"),
        SELECT("SELECT driver_name FROM dc_connection WHERE id_driver = ?"),
        UPDATE("UPDATE dc_connection SET driver_name = ? WHERE id_driver = ?"),
        DELETE("DELETE FROM dc_connection WHERE id_driver = ?");

        String QUERY;

        SQLConDrCar(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}

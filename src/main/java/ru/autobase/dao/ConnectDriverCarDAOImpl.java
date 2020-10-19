package main.java.ru.autobase.dao;

import main.java.ru.autobase.entity.ConnectDriverCar;

import java.sql.*;
import java.util.*;

public class ConnectDriverCarDAOImpl implements ConnectDriverCarDAO{

    private final Connection connection;

    public ConnectDriverCarDAOImpl(final Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(ConnectDriverCar conDrCar) {
        try (PreparedStatement prepStat = connection.prepareStatement(SQLConDrCar.INSERT.QUERY)) {
            prepStat.setInt(1, conDrCar.getIdDriverCon());
            prepStat.setInt(2, conDrCar.getIdCarCon());
            prepStat.setInt(3, conDrCar.getIdDriverCon());
            prepStat.setInt(4, conDrCar.getIdCarCon());
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Query error, try again");
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

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQLConDrCar.SELECT.QUERY);

            while (rs.next()) {
                ConnectDriverCar conDrCar = new ConnectDriverCar();
                conDrCar.setIdDriverCon(rs.getInt("id_d_con"));
                conDrCar.setIdCarCon(rs.getInt("id_c_con"));
                conDrCarList.add(conDrCar);
            }
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
        return conDrCarList;
    }


    @Override
    public Map<String, String> getAllWithNames() {
        List<String> conDrCarList = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQLConDrCar.SELECT_NAMES.QUERY);
            rs.next();
            while (rs.next()) {
                conDrCarList.add(rs.getString("driver_name") + " " + rs.getString("car_number"));
            }
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }

        Map<String, String> conDrCarMap = new HashMap<>();
        String carNumber = "";
        for (String con : conDrCarList) {
            Scanner scan = new Scanner(con);
            String driverName = scan.next() + " " + scan.next();

            if (!conDrCarMap.containsKey(driverName)) {
                carNumber = "";
            }
            carNumber = carNumber + " " + scan.next();
            conDrCarMap.put(driverName, carNumber);
            scan.close();
        }
        return conDrCarMap;
    }


    @Override
    public void update(ConnectDriverCar conDrCar) {
        //meaningless method in case of multiple connection, better delete one and create new
    }

    @Override
    public void delete(ConnectDriverCar conDrCar) {
        try (PreparedStatement prepStat = connection.prepareStatement(SQLConDrCar.DELETE.QUERY)) {
            prepStat.setInt(1, conDrCar.getIdDriverCon());
            prepStat.setInt(2, conDrCar.getIdCarCon());
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Query error, try again");
        }
    }

    enum SQLConDrCar {
        INSERT("INSERT INTO dc_connection (id_d_con, id_c_con) SELECT ?, ? " +
                "WHERE NOT EXISTS (SELECT id_d_con, id_c_con FROM dc_connection " +
                "WHERE id_d_con = ? AND id_c_con = ?) LIMIT 1"),
        SELECT("SELECT * FROM dc_connection ORDER BY id_d_con"),
        SELECT_NAMES("SELECT driver_name, car_number FROM drivers " +
                "JOIN dc_connection ON id_driver = id_d_con " +
                "JOIN car_info ON id_c_con = id_car ORDER BY driver_name"),
        DELETE("DELETE FROM dc_connection WHERE id_d_con = ? AND id_c_con = ?");

        String QUERY;

        SQLConDrCar(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}

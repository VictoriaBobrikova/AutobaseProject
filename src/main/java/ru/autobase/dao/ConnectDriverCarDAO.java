package main.java.ru.autobase.dao;

import main.java.ru.autobase.entity.Car;
import main.java.ru.autobase.entity.ConnectDriverCar;
import main.java.ru.autobase.entity.Driver;

import java.util.List;
import java.util.Map;

public interface ConnectDriverCarDAO extends GeneralDAO<ConnectDriverCar, ConnectDriverCar> {
    //List<Driver> getDriverWithMaxCountCars();
    List<Car> getCarsByDriverId(Integer id);
    List<Driver> getDriversByCarId(Integer id);
    Map<String, String> getAllWithNames();
}

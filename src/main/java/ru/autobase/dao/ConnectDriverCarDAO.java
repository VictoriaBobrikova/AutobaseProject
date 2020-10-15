package main.java.ru.autobase.dao;

import main.java.ru.autobase.entity.ConnectDriverCar;

import java.util.Map;

public interface ConnectDriverCarDAO extends GeneralDAO<ConnectDriverCar, ConnectDriverCar> {
    //getDriverWithMaxCars
    //List<Car> getCarsByDriverId(Integer id);
    //List<Driver> getDriversByCarId(Integer id);
    Map<String, StringBuilder> getAllWithNames();
}

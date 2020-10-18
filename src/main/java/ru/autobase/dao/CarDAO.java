package main.java.ru.autobase.dao;

import main.java.ru.autobase.entity.Car;

import java.util.List;

public interface CarDAO extends GeneralDAO<Car, Integer> {
    List<Car> getByCarMark(String carMark);
    List<Car> getByDriverName(String driverName);
}

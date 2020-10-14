package main.java.ru.autobase.dao;

import main.java.ru.autobase.entity.Car;

public interface CarDAO extends GeneralDAO<Car, Integer> {
    Car getByCarMark(String carMark);
    Car getByDriverName(String driverName);
}

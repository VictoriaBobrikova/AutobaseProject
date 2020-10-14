package main.java.ru.autobase.dao;

import main.java.ru.autobase.entity.Driver;

import java.util.List;

public interface DriverDAO extends GeneralDAO<Driver, Integer> {
    List<Driver> getAllAlphabetOrder();
    Driver getByCarMark(String carMark);
    Driver getByCarNumber(String carNumber);
}

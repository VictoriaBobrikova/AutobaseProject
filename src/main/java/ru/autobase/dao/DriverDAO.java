package main.java.ru.autobase.dao;

import main.java.ru.autobase.entity.Driver;

import java.util.List;

public interface DriverDAO extends GeneralDAO<Driver, Integer> {
    List<Driver> getAllAlphabetOrder();
    List<Driver> getByCarMark(String carMark);
    List<Driver> getByCarNumber(String carNumber);
    List<Driver> getByCarId(Integer id);
}

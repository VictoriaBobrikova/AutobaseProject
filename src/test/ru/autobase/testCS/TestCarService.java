package ru.autobase.testCS;

import main.java.ru.autobase.entity.Car;
import main.java.ru.autobase.service.CarService;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class TestCarService {
    String carNumber = "а123хх";
    String newCarNumber = "z000zz";
    String carMark = "BMW";
    String newCarMark = "Audi";

    String driverName = "Ivan Ivanov";

    Integer idCar = 1;
    Integer idDriver = 1;


    @Test(priority = 1)
    public void testGetAll() {
        boolean ifEmpty = false;
        List<Car> carListAll = CarService.getAllService();
        if(carListAll.isEmpty()) ifEmpty = true;
        Assert.assertFalse(ifEmpty);
    }

    @Test
    public void testGetById() {
        Car carAct = CarService.getByIdService(idCar);
        Assert.assertEquals(carAct.getCarNumber(), carNumber);
        Assert.assertEquals(carAct.getCarMark(), carMark);
    }


    @Test(dependsOnMethods = "testGetAll")
    public void testGetByCarMark() {
        List<Car> carListAll = CarService.getAllService();
        List<Car> carExp = new ArrayList<>();
        for (Car c : carListAll) {
            if (c.getCarMark().equals(carMark)) {
                carExp.add(c);
            }
        }
        List<Car> carAct = CarService.getByCarMarkService(carMark);
        Assert.assertEquals(carAct, carExp);
    }


    @Test(groups = "create", dependsOnMethods = "testGetByCarMark")
    public void testCreate() {
        List<Car> carListBef = CarService.getByCarMarkService(newCarMark);
        boolean ifExists = false;
        for (Car c : carListBef) {
            if (c.getCarNumber().equals(newCarNumber)) {
                ifExists = true;
                break;
            }
        }
        Assert.assertFalse(ifExists);

        Car carNew = new Car(newCarNumber, newCarMark);
        CarService.createService(carNew);
        List<Car> carListAft = CarService.getByCarMarkService("carMark");
        for (Car c : carListAft) {
            if (c.getCarNumber().equals(newCarNumber) && c.getCarMark().equals(newCarMark)) {
                ifExists = true;
                break;
            }
        }
        Assert.assertTrue(ifExists);
    }


    @Test(groups = "create", dependsOnMethods = "testCreate")
    public void testUpdate() {
        List<Car> carListBef = CarService.getByCarMarkService(newCarMark);
        for (Car c : carListBef) {
            if (c.getCarNumber().equals(newCarNumber)) {
                idCar = c.getIdCar();
            }
        }
        Car carUpd = new Car(idCar,"w999ww");
        CarService.updateService(carUpd);
        Car carAct = CarService.getByIdService(idCar);
        Assert.assertEquals(carAct.getCarNumber(),"w999ww");
        Assert.assertEquals(carAct.getCarMark(), newCarMark);

    }

    @Test(groups = "create", dependsOnMethods = "testUpdate")
    public void testDelete() {
        List<Car> carListBef = CarService.getByCarMarkService(newCarMark);
        for (Car c : carListBef) {
            if (c.getCarNumber().equals("w999ww")) {
                idCar = c.getIdCar();
            }
        }
        CarService.deleteService(idCar);
        Car carAct = CarService.getByIdService(idCar);
        boolean ifDelete = true;
        if (carAct.getCarMark().isEmpty() && carAct.getCarNumber().isEmpty()) ifDelete = false;
        Assert.assertTrue(ifDelete);
    }

    List<Car> carExp = new ArrayList<>();

    @BeforeGroups(groups = "with driver")
    public List<Car> createCarExpList() {
        carExp.add(new Car("а123хх", "BMW"));
        carExp.add(new Car("с888сс", "Skoda"));
        return carExp;
    }

    @Test(groups = "with driver")
    public void testGetByDriverName() {
        List<Car> carAct = CarService.getByDriverNameService(driverName);
        Assert.assertEquals(carAct, carExp);
    }

    @Test(groups = "with driver")
    public void testGetByDriverId() {
        List<Car> carAct = CarService.getByDriverIdService(idDriver);
        Assert.assertEquals(carAct, carExp);
    }
}

package main.java.ru.autobase.entity;

public class Car {
    private Integer idCar;
    private String carNumber;
    private String carMark;

    public Car() {
    }

    public Car(Integer idCar) {
        this.idCar = idCar;
    }

    public Car(String carNumber) {
        this.carNumber = carNumber;
    }

    public Car(String carNumber, String carMark) {
        this.carNumber = carNumber;
        this.carMark = carMark;
    }

    public Car(Integer idCar, String carNumber) {
        this.idCar = idCar;
        this.carNumber = carNumber;
    }

    public Car(Integer idCar, String carNumber, String carMark) {
        this.idCar = idCar;
        this.carNumber = carNumber;
        this.carMark = carMark;
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarMark() {
        return carMark;
    }

    public void setCarMark(String carMark) {
        this.carMark = carMark;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return idCar.equals(car.idCar);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idCar;
        return result;
    }
}

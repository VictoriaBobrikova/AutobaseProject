package main.java.ru.autobase.entity;

public class Driver {
    private Integer idDriver;
    private String driverName;

    public Driver() {
    }
    public Driver(Integer idDriver) {
        this.idDriver = idDriver;
    }
    public Driver(String driverName) {
        this.driverName = driverName;
    }

    public Driver(Integer idDriver, String driverName) {
        this.idDriver = idDriver;
        this.driverName = driverName;
    }

    public Integer getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(Integer idDriver) {
        this.idDriver = idDriver;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}

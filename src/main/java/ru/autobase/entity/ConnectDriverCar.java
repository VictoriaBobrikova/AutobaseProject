package main.java.ru.autobase.entity;

public class ConnectDriverCar {
    private Integer idDriverCon;
    private Integer idCarCon;

    public ConnectDriverCar() {
    }

    public ConnectDriverCar(Integer idDriverCon, Integer idCarCon) {
        this.idDriverCon = idDriverCon;
        this.idCarCon = idCarCon;
    }

    public Integer getIdDriverCon() {
        return idDriverCon;
    }
     public void setIdDriverCon(Integer idDriverCon) {
        this.idDriverCon = idDriverCon;
     }

    public Integer getIdCarCon() {
        return idCarCon;
    }
    public void setIdCarCon(Integer idCarCon) {
        this.idCarCon = idCarCon;
    }

}

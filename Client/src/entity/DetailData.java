package entity;

public class DetailData {
    private String name;
    private String producer;
    private String mark;
    private String model;
    private String typeMotor;
    private double volumeMotor;
    private int price;
    private int indexDetail;
    private int indexCar;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTypeMotor() {
        return typeMotor;
    }

    public void setTypeMotor(String typeMotor) {
        this.typeMotor = typeMotor;
    }

    public double getVolumeMotor() {
        return volumeMotor;
    }

    public void setVolumeMotor(double volumeMotor) {
        this.volumeMotor = volumeMotor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIndexDetail() {
        return indexDetail;
    }

    public void setIndexDetail(int indexDetail) {
        this.indexDetail = indexDetail;
    }

    public int getIndexCar() {
        return indexCar;
    }

    public void setIndexCar(int indexCar) {
        this.indexCar = indexCar;
    }

    public String toString(){
        return name;
    }
}

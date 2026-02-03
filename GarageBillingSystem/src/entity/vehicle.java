package entity;

public class vehicle {
    private int customerId;
    private int id;
    private String number_plate;
    private String model;

    public vehicle() {}
    public vehicle(int customerId, int id, String number_plate, String model) {
        this.customerId = customerId;
        this.id = id;
        this.number_plate = number_plate;
        this.model = model;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber_plate() {
        return number_plate;
    }

    public void setNumber_plate(String number_plate) {
        this.number_plate = number_plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "vehicle{" +
                "customerId=" + customerId +
                ", id=" + id +
                ", number_plate='" + number_plate +
                ", model='" + model + '}';
    }
}
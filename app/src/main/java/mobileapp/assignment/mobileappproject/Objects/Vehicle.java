package mobileapp.assignment.mobileappproject.Objects;

public class Vehicle {

    private String vehicleManufacturer;
    private String vehicleName;
    private String vehicleCategory;
    private String registration;
    private String owner;
    private String registrationDate;


    public Vehicle(String vehicleManufacturer, String vehicleName, String vehicleCategory, String registration, String owner, String registrationDate){

        this.vehicleManufacturer = vehicleManufacturer;
        this.vehicleName = vehicleName;
        this.vehicleCategory = vehicleCategory;
        this.registration = registration;
        this.owner = owner;
        this.registrationDate = registrationDate;

    }


    public String getVehicleManufacturer() {
        return vehicleManufacturer;
    }
    public String getVehicleName() {
        return vehicleName;
    }
    public String getVehicleCategory() {
        return vehicleCategory;
    }
    public String getRegistration() {
        return registration;
    }
    public String getRegistrationDate() {
        return registrationDate;
    }
    public String getOwner() {
        return owner;
    }

}
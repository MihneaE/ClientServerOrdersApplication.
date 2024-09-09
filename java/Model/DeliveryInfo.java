package Model;

import java.io.Serializable;
import java.util.Objects;

public class DeliveryInfo implements Cloneable, Serializable {
    private String phone;
    private String address;
    private String apartment;
    private String floor;
    private String otherDetails;

    public DeliveryInfo()
    {}

    public DeliveryInfo(String phone, String address, String apartment, String floor, String otherDetails)
    {
        this.phone = phone;
        this.address = address;
        this.apartment = apartment;
        this.floor = floor;
        this.otherDetails = otherDetails;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }

    public String getFloor() {
        return floor;
    }

    public String getApartment() {
        return apartment;
    }

    public String getPhone() {
        return phone;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryInfo that = (DeliveryInfo) o;
        return Objects.equals(phone, that.phone) && Objects.equals(address, that.address) && Objects.equals(apartment, that.apartment) && Objects.equals(floor, that.floor) && Objects.equals(otherDetails, that.otherDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, address, apartment, floor, otherDetails);
    }

    @Override
    public String toString() {
        return "DeliveryInfo{" +
                "phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", apartment='" + apartment + '\'' +
                ", floor='" + floor + '\'' +
                ", otherDetails='" + otherDetails + '\'' +
                '}';
    }

    @Override
    public DeliveryInfo clone() {
        try {

            return (DeliveryInfo) super.clone();
        } catch (CloneNotSupportedException e) {

            throw new RuntimeException("This class does not implement Cloneable", e);
        }
    }
}

package Model;

import java.io.Serializable;
import java.util.Objects;

public class DeliveryAccount implements Serializable {
    private String name;
    private String gmail;
    private String password;

    public DeliveryAccount() {}

    public DeliveryAccount(String name, String gmail, String password)
    {
        this.name = name;
        this.gmail = gmail;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeliveryAccount{" +
                "name='" + name + '\'' +
                ", gmail='" + gmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryAccount that = (DeliveryAccount) o;
        return Objects.equals(name, that.name) && Objects.equals(gmail, that.gmail) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gmail, password);
    }
}

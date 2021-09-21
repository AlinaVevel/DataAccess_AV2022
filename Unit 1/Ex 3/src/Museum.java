import java.util.ArrayList;
import java.util.List;

public class Museum {
    protected String name;
    protected String address;
    protected String city;
    protected String country;
    protected List<Hall> listOfHall;

    public List<Hall> getListOfHall() {
        return listOfHall;
    }

    public Museum(){

        listOfHall = new ArrayList<>();
    }

    public void addHallAtMuseum(Hall hall){
        hall.isInMuseum(this);
        listOfHall.add(hall);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}

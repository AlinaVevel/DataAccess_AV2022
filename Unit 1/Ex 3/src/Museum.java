public class Museum {
    protected String name;
    protected String address;
    protected String city;
    protected String country;
    protected Hall ubication;

    public Museum(){

        ubication = new Hall();
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

    public Hall getHall() {
        return ubication;
    }

    public void setHall(Hall hall) {
        ubication = hall;
    }
}

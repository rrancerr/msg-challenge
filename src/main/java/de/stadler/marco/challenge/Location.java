package de.stadler.marco.challenge;

public class Location {
    private final int number;
    private final String msgLocationName;
    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;
    private final double latitude;
    private final double longitude;

    public Location(int number, String msgLocationName, String street, String houseNumber, String postalCode, String city, double latitude, double longitude) {
        this.number = number;
        this.msgLocationName = msgLocationName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "number=" + number +
                ", msgLocationName='" + msgLocationName + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public int getNumber() {
        return number;
    }

    public String getMsgLocationName() {
        return msgLocationName;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

package pizzarat.cs2340.gatech.edu.Model;

import pizzarat.cs2340.gatech.edu.Model.Borough;

/**
 * @author Harrison Banh
 * This class is a container or holder for all the information related to making
 * a rat sighting report.
 */
public class RatSightingReport {
    private int key;                    // Unique key assigned to each report
    private String location;            // Latitude and longitude of the sighting
    private String date;                // May need to change later
    private String time;                   // May need to change later
    private String address;             // The address of the sighting
    private String zipCode;                // The zip code of the rat report
    private String city;                // The city of rat report
    private String borough;            // The borough of the rat sighting;

    /**
     * Creates the most specific rat sighting report by specifying all fields.
     * @param key the unique key assigned to each report
     * @param location the latitude and longitude of the sighting
     * @param date the date of the sighting
     * @param time the time of the sighting
     * @param address the address of the sighting
     * @param zipCode the zip code portion of the address of the sighting
     * @param city the city portion of the address of the sighting
     * @param borough the borough in New York where the sighting occurred
     */
    public RatSightingReport(int key, String location, String date, String time,
                             String address, String zipCode, String city,
                             String borough) {
        this.key = key;
        this.location = location;
        this.date = date;
        this.time = time;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.borough = borough;
    }

    /**
     * Returns unique key of the rat sighting.
     * @return the unique key of the sighting
     */
    public int getKey() {
        return key;
    }

    /**
     * Sets the specified key for this rat sighting.
     * @param key
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Returns the latitude and longitude of the rat sighting.
     * @return the latitude and longitude of the rat sighting
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the latitude and longitude of this rat sighting.
     * @param location the latitude and longitude of the sighting
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the date of the rat sighting report.
     * @return the date of the rat sighting
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of this rat sighting report.
     * @param date the specified date of the rat sighting
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the time of this rat sighting report.
     * @return the time of the rat report
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time of this rat report.
     * @param time the specified time of the report
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the address of this rat sighting report.
     * @return the address of this rat report
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of this rat report to the specified address.
     * @param address the specified address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the zip code of this rat report.
     * @return the zip code of the rat report
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code of the rat report to the specified zip code.
     * @param zipCode the specified zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the city of the location of the rat report.
     * @return the city of the rat report
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the rat report to the specified city
     * @param city the specified city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the borough of the rat report
     * @return the borough of the rat report
     */
    public String getBorough() {
        return borough;
    }

    /**
     * Sets the borough of the rat report to the specified borough
     * @param borough the specified borough
     */
    public void setBorough(String borough) {
        this.borough = borough;
    }
}

package learn.memories.service;

import learn.memories.service.dto.HourlyData;
import learn.memories.service.dto.HourlyUnits;

public class WeatherResponse {
    public double latitude;
    public double longitude;
    // Jackson will map 'hourly_units' to this field
    public HourlyUnits hourlyUnits;
    public HourlyData hourly;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public HourlyUnits getHourlyUnits() {
        return hourlyUnits;
    }

    public void setHourlyUnits(HourlyUnits hourlyUnits) {
        this.hourlyUnits = hourlyUnits;
    }

    public HourlyData getHourly() {
        return hourly;
    }

    public void setHourly(HourlyData hourly) {
        this.hourly = hourly;
    }
}

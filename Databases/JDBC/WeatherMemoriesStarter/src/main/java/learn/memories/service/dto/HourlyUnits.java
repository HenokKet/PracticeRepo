package learn.memories.service.dto;

public class HourlyUnits {
    public String time;
    // Jackson will map 'temperature_2m' to this field
    public String temperature_2m;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(String temperature_2m) {
        this.temperature_2m = temperature_2m;
    }
}

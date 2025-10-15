package learn.memories.service.dto;

import java.util.List;

public class HourlyData {
    // List to store the timestamp strings
    public List<String> time;
    // List to store the temperature values
    public List<Double> temperature_2m;

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<Double> getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(List<Double> temperature_2m) {
        this.temperature_2m = temperature_2m;
    }
}
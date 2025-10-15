package learn.memories.domain;

import learn.memories.service.WeatherClient;
import learn.memories.service.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class WeatherService {

    public double getAverageTemperaturesForDate(LocalDate start, LocalDate end) {
        RestTemplate demoRestTemplate = new RestTemplate();

        WeatherClient client = new WeatherClient(demoRestTemplate);

        // --- API Parameters ---
        //This is Milwaukee
        double lat = 43.04;
        double lon = -87.91;

        WeatherResponse weatherData = client.getHourlyTemperature(lat, lon, start, end);

        if (weatherData != null) {
//            System.out.println("\n--- Weather Data Received ---");
//            System.out.println("Latitude: " + weatherData.latitude);
//            System.out.println("Longitude: " + weatherData.longitude);
            double tempTotal = 0.0;
            for(double temperature : weatherData.hourly.getTemperature_2m()){
                tempTotal += temperature;
            }
            return  tempTotal/weatherData.hourly.getTemperature_2m().size();

        } else {
            System.out.println("Failed to retrieve weather data.");
            return 0.0;
        }

    }
}

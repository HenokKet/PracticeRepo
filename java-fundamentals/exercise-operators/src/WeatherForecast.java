public class WeatherForecast {
    public static void main(String[] args) {
//        Declare and initialize variables:
        double temperatureCelsius = 25;
        int temperatureFahrenheit = 0;
        int wind = 20;
        boolean goodWeather,highWind,heat,cold;
        boolean rain = false;
//        1. Convert Celsius to Fahrenheit using the formula:
        temperatureFahrenheit = (int)(temperatureCelsius * (1.8)) + 32;
//        2. Use Assignment Operators:
//               Increase temperature by 5 degrees (+=).
//               Increase wind speed by 5 km/h (+=).
            temperatureFahrenheit += 5;
            wind +=5;
//        3. Use Comparison Operators:
//               Check if the temperature in Fahrenheit exceeds 85°F.
//               Check if the wind speed is greater than 20 km/h.
        if (temperatureFahrenheit > 85) {
            heat = true;
            System.out.println("It is hot outside");
        } else {
            heat = false;
            System.out.println("It is not hot outside");
        }
        if (wind > 20) {
            highWind = true;
            System.out.println("It is windy outside");
        } else {
            highWind = false;
            System.out.println("It is not windy outside");
        }
//        4. Use Logical Operators:
//               Determine if it's a good day to go outside (not raining AND temperature
//               between 60°F - 85°F).
        if(!rain && (temperatureFahrenheit>60 &&temperatureFahrenheit<85)){
            goodWeather = true;
            System.out.println("It is a good day to be outside");
        } else{
            goodWeather = false;
            System.out.println("It is not a good day to be outside");
        }
//    Determine if there's a weather warning (wind speed above 30 km/h OR
//        temperature below 0°C).
        if(wind > 30 && temperatureCelsius < 0){
            goodWeather = false;
            System.out.println("WEATHER WARNING BE CAREFUL");
        }
//        5. Print the results in a readable format.
//
        System.out.println("Todays Weather is " + temperatureFahrenheit + " degrees" );
    }
}

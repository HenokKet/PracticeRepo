public class TrafficLight {
    public static void main(String[] args) {
    //Traffic lights are programmed using an indexed array.

    //1. Create an enum named TrafficLight with values:
        //RED
        //YELLOW
        //GREEN
        enum Light{
            RED,
            YELLOW,
            GREEN
        }
    //2. Store all values in an array using values().
        Light[] arrLights = Light.values();
    //3. Retrieve the correct signal based on a predefined index (1 for YELLOW).
        int index = 1;
        Light signal = arrLights[1];
    //4. Print the selected signal.
        System.out.println(signal);
    }
}

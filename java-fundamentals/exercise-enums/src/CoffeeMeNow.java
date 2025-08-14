public class CoffeeMeNow {
    public static void main(String[] args) {
        //A café uses enums to manage coffee sizes.

        //1. Create an enum named CoffeeSize with three constants:
            //SMALL
            //MEDIUM
            //LARGE
        enum CoffeeSize{
            TALL,
            GRANDE,
            VENTI
        }

        //2. Declare and initialize a variable of type CoffeeSize to store a predefined coffee order.
        CoffeeSize myOrder = CoffeeSize.VENTI;
        //3. Print the selected coffee size.
        System.out.println("Selected coffee size: " + myOrder);
    }
}

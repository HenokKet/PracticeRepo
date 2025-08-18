package org.example.shoppingcart;

/**
 * If tax-exempt don't charge tax.
 * If order total is more than $100 apply discount 5%
 * If order total is more than $500 apply discount 10%
 * If promo code is valid, apply free shipping (unless next-day or 2-day)
 * Henok Ketema
 */
public class ShoppingCartApp {

    public static void main(String[] args) {
        System.out.println("Welcome to the shopping cart app!");

        java.util.Scanner console = new java.util.Scanner(System.in);

        double productPrice = 499.99;
        int productQuantity = 5;
        double totalCost = productPrice * productQuantity;
        //new variables
        double subtotal = totalCost;
        double tax = .07;
        double standard = 2.00;
        double twoDay = 5.00;
        double overnight = 10.00;
        double discount = 0;
        String promo = "FREESHIP";

        //reformatted output statements
        System.out.println("Product Price: $" + String.format("%.2f",productPrice));
        System.out.println("Product Quantity: " + productQuantity);
        System.out.println("Subtotal: $" + String.format("%.2f",totalCost));

        // Prompt for tax exempt
        System.out.print("Are you tax-exempt? (y/n) ");
        String taxExempt = console.nextLine();

        // Prompt for shipping
        System.out.print("Shipping? (standard / overnight / two-day) ");
        String shipping = console.nextLine();

        // Prompt for promo code
        System.out.print("Promo code for free shipping? ");
        String promoCode = console.nextLine();

        //calculating total tax
        double totalTax = totalCost * tax;
        if(taxExempt.equals("y")){
            totalTax = 0;
        }

        //calculate discount
        if(totalCost > 500){
            discount += totalCost *.1;
            totalCost = totalCost *.9;
        } else if (totalCost > 100) {
            discount += totalCost *.05;
            totalCost = totalCost *.95;
        }
        //calculate shipping
        double shippingCost = 0;
        switch (shipping){
            case "standard":
                shippingCost = standard;
                break;
            case "overnight":
                shippingCost = overnight;
                break;
            case "two-day":
                shippingCost = twoDay;
                break;
            default:
                System.out.println("Invaild Shipping Type");
        }
        //calculate discount with promo code
        if (promoCode.equals(promo)) {
            if (shippingCost == 2.00) {
                shippingCost = 0;
                discount += 2;
            }
        }
        //calculating total cost with tax and shipping
        totalCost = totalCost + totalTax + shippingCost;
        // Print details
        System.out.println("\nDetails:");
        System.out.println("Tax-exempt: " + taxExempt);
        System.out.println("Shipping: " + shipping);
        System.out.println("Promo code: " + promoCode);
        // Separated Numerical details into a receipt
        System.out.println("Order Receipt:");
        System.out.println("Subtotal: $" + String.format("%.2f",subtotal));
        System.out.println("Shipping Cost: $" +String.format("%.2f", shippingCost));
        System.out.println("Tax: $" + String.format("%.2f",totalTax));
        System.out.println("Discounted: -$" + String.format("%.2f",discount));
        System.out.println("Total Price: $" + String.format("%.2f",totalCost));


        System.out.println("Bye");
    }
}
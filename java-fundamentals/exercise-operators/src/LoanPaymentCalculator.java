public class LoanPaymentCalculator {

    public static void main(String[] args) {
        //Declare and initialize variables:
        int loanAmount = 100000;
        double interestRate = 5.0;
        double monthlyPayment = (loanAmount*(interestRate/100)/12);
        monthlyPayment = Math.round(monthlyPayment*100.00)/100.00;
        int loanTermYears =  10;
//        Calculate Monthly Payment using the Formula
//        monthlyPayment = (loanAmount×(interestRate/100)/12)
//        1. Use Assignment Operators:
//             Increase loanAmount by $5,000 (+=).
//             Reduce the annualInterestRate by 1% (-=).
//             Increase loanTermYears by 1 (++).
        loanAmount+= 5000;
        interestRate -=1;
        loanTermYears ++;

//
//         2. Use Comparison Operators:
//             Check if the loanAmount exceeds $30,000.
//             Check if the monthlyPayment is more than $500.
        if (loanAmount > 30000){
            System.out.println("Your loan is above $30,000");
        } else {
            System.out.println("Your loan is under $30,000");
        }
//          3. Use Logical Operators:
//            Determine if the loan is affordable (monthly payment is below $500 AND
//                    term is over 3 years).
//            Determine if the loan is expensive (monthly payment is above $700 OR
//                    interest rate is over 6%).
//
        if(monthlyPayment<500 && loanTermYears > 3){
            System.out.println("Your loan is affordable");
        }else{
            System.out.println("Your loan is not affordable");
        }
        if(monthlyPayment>700 && interestRate > 6){
            System.out.println("Your loan is expensive");
        }else{
            System.out.println("Your loan is not expensive");
        }
//           4. Print all results.
        System.out.println("Hello! You borrowed $" + loanAmount + " with a "+ interestRate +"% intrest rate." );
        System.out.println("Your monthly payment is $" + monthlyPayment + " over " +loanTermYears + " years.");
    }

}
